// run me first (before golden2.c)

#include <stdio.h>
#include "fileio.h"

int main(int argc, char *argv[]) {
  File f;
  f=open_file("golden.dat");
  fs_print_error();
  write_file_at(f, "M", 1, BEGINNING_OF_FILE, 0L);
  fs_print_error();
  close_file(f);
  fs_print_error();
  return 0;
}
  

  

