#include <stdio.h>
#include "fileio.h"

int main(int argc, char *argv[]) {
  File f1, f2;
  f1=open_file("kfile1.dat");
  f2=open_file("kfile2.dat");
  fs_print_error();
  write_file_at(f1, "MOM", strlen("MOM"), BEGINNING_OF_FILE, 0L);
  fs_print_error();
  write_file_at(f2, "ZEBRA", strlen("ZEBRA"), BEGINNING_OF_FILE, 0L);
  fs_print_error();
  close_file(f1);
  close_file(f2);
  fs_print_error();
  // the epic swapperoo
  f1=open_file("kfile2.dat");
  f2=open_file("kfile1.dat");
  write_file_at(f2, "Z", strlen("Z"), BEGINNING_OF_FILE, 1L);
  fs_print_error();
  write_file_at(f1, "Z", strlen("Z"), BEGINNING_OF_FILE, 1L);
  fs_print_error();
  close_file(f1);
  close_file(f2);
  fs_print_error();

  return 0;
}
