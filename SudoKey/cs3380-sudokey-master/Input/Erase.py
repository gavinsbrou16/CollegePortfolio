import tkinter

from Input.InputNum import InputNum

#Leopold Marx, Quoc Than, Gavin Broussard
#This class handels what happens when you erase a cell
class Erase(InputNum):
    def __init__(self, inputfield):
        self.inputfield = inputfield

    def click(self):
        self.inputfield.window.board.changeCellVal(0)