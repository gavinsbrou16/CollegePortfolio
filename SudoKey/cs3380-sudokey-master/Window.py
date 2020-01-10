from tkinter import *

from Board import Board
from Controller import Controller
from InputField import InputField

#Leopold Marx, Quoc Than, Gavin Broussard
# This class is simply to handle the window
class Window:

    def __init__(self, master):

        self.master = master
        self.main = Frame(master= self.master)
        self.main.pack()

        self.controller = Controller(self)

        self.dim = 500
        self.pad = 20

        self.board = Board(self, self.dim, self.pad)
        self.inputfield = InputField(self, self.dim, self.pad)

        master.columnconfigure(1, weight=1)
        master.rowconfigure(0, weight=1)

        master.title("Sudokey")

if __name__ == "__main__":
    root = Tk()
    my_gui = Window(root)
    root.mainloop()