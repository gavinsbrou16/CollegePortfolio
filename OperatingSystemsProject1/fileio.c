//////////////////////////////////////////////////////////////////////
// Intentionally flawed system call library that implements          //
// (unfortunately, not) "safe" file I/O, "preventing" writing "MZ"   //
// at the beginning of a file.                                       //
//                                                                   //
// Written by Golden G. Richard III (@nolaforensix), 7/2017          //
//                                                                   //
// Props to Brian Hay for a similar exercise he used in a recent     //
// training.                                                        //
///////////////////////////////////////////////////////////////////////

//edited by Gavin Broussard

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fileio.h"

//
// GLOBALS
//

FSError fserror;

//
// private functions
//

static int seek_file(File file, SeekAnchor start, long offset) {
if (!file -> ptr || (start != BEGINNING_OF_FILE &&
           start != CURRENT_POSITION && start != END_OF_FILE)) {
    return 0;
}
else {
    if (! fseek(file -> ptr, offset, start == BEGINNING_OF_FILE ? SEEK_SET :
       (start == END_OF_FILE ? SEEK_END : SEEK_CUR))) {
      return 1;
    }
    else {
      return 0;
    }
}
}

File open_file(char *name) {
   File fp;
   fp = malloc(sizeof(FileInternal));

   fserror=NONE;
// try to open existing file
   fp -> ptr =fopen(name, "r+");
   if (! fp -> ptr) {
    // fail, fall back to creation
   fp -> ptr=fopen(name, "w+");
   if (! fp -> ptr) {
           fserror=OPEN_FAILED;
           return NULL;
        }
        }
   read_file_from(fp, fp -> m, 2L, BEGINNING_OF_FILE, 0L);
   return fp;
   }

// close a 'file'. If the close operation fails, the global 'fserror'
// is set to CLOSE_FAILED, otherwise to NONE.
void close_file(File file) {
if (file -> ptr && ! fclose(file -> ptr)) {
    free(file);
    fserror=NONE;
}
else {
    fserror=CLOSE_FAILED;
}
}

unsigned long read_file_from(File file, void *data, unsigned long num_bytes,
                 SeekAnchor start, long offset) {

unsigned long bytes_read=0L;

fserror=NONE;
if (! file -> ptr || ! seek_file(file, start, offset)) {
    fserror=READ_FAILED;
}
else {
    bytes_read=fread(data, 1, num_bytes, file -> ptr);
    if (ferror(file -> ptr)) {
      fserror=READ_FAILED;
    }
}
return bytes_read;
}

unsigned long write_file_at(File file, void *data, unsigned long num_bytes, SeekAnchor start, long offset) {

   unsigned long bytes_written=0L;
   fserror=NONE;
   char *buf = (char*) data;

   if (! file -> ptr || ! seek_file(file, start, offset)) {
       fserror=WRITE_FAILED;
   }
   else{
       long p = ftell(file -> ptr);
       //printf("p = %ld\n",p);
       //printf("offset = %ld\n",offset);
       if(p == 0L && (!strncmp(data, "MZ",2))){
           fserror=ILLEGAL_MZ;
       }
       //if there is 'Z' in second spot of file, and the buffer is 'M'
       //this results in ILLEGAL_MZ
       else if(p == 0L && file->m[1] == 'Z' && buf[0] == 'M'){
           fserror = ILLEGAL_MZ;
           //printf("tried to put M infront of Z\n");
       }
       //if position is 1, and the file ptr for m[0] is 'M'
       //and the buffer is
       else if(p == 1L && file->m[0] == 'M' && buf[0] == 'Z'){
           fserror = ILLEGAL_MZ;
           //printf("tried to put Z behind M\n");
       }
       else{
           if (p == 0L && buf[0] == 'M'){
               file->m[0] = 'M';
               //printf("m0 is M\n");
           }
           else if(p == 0L && buf[1] == 'Z'){
               file->m[1] = 'Z';
               //printf("m1 is Z\n");
           }
           else if(p == 1L && buf[0] == 'Z'){
                file->m[1] = 'Z';
                //printf("m1 is Z\n");
           }
           bytes_written=fwrite(data, 1, num_bytes, file -> ptr);
           if (bytes_written < num_bytes){
               fserror=WRITE_FAILED;
           }
       }
   }
   //printf("%ld was written", bytes_written);
   return bytes_written;
}

void fs_print_error(void) {
printf("FS ERROR: ");
switch (fserror) {
   case NONE:
    puts("NONE");
    break;
case OPEN_FAILED:
    puts("OPEN_FAILED");
    break;
case CLOSE_FAILED:
    puts("CLOSE_FAILED");
    break;
case READ_FAILED:
    puts("READ_FAILED");
    break;
case WRITE_FAILED:
    puts("WRITE_FAILED");
    break;
case ILLEGAL_MZ:
    puts("ILLEGAL_MZ: SHAME ON YOU!");
break;
default:
    puts("UNKNOWN ERROR");
}
}
