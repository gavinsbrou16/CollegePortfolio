///////////////////////////////////////////////////////////////////////
// Intentionally flawed system call library that implements          //
// "safe" (not) file I/O, "preventing" writing "MZ" at the beginning //
// of a file.                                                        //
//                                                                   //
// Written by Golden G. Richard III (@nolaforensix), 7/2017          //
//                                                                   //
// Props to Brian Hay for a similar exercise he used in a recent     //
// training.                                                        //
///////////////////////////////////////////////////////////////////////

//Edited by Gavin Broussard


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// filesystem error code
typedef enum {
NONE,
OPEN_FAILED,
CLOSE_FAILED,
READ_FAILED,
WRITE_FAILED,
ILLEGAL_MZ
} FSError;

// file handle type
//typedef FileInternal* File;
typedef struct _FileInternal{
   FILE* ptr;
   char m[2];
} FileInternal;

typedef FileInternal* File;


// seek anchors
typedef enum {
BEGINNING_OF_FILE,
CURRENT_POSITION,
END_OF_FILE
} SeekAnchor;

File open_file(char *name);

// close file with handle 'file'. Always sets 'fserror' global.
void close_file(File file);

unsigned long read_file_from(File file, void *data, unsigned long num_bytes,
                 SeekAnchor start, long offset);

unsigned long write_file_at(File file, void *data, unsigned long num_bytes,
                SeekAnchor start, long offset);

// describe current filesystem error code
void fs_print_error(void);

// GLOBALS //

// filesystem error code set (set by each function)
extern FSError fserror;
