
from Input.InputNum import InputNum

#Leopold Marx, Quoc Than, Gavin Broussard
# Num is a class that handles clicks for numbers 1-9.
class Num(InputNum):
    def __init__(self, inputfield, num):
        self.val = num
        self.inputfield = inputfield

    def click(self):
        self.inputfield.window.board.changeCellVal(self.val)