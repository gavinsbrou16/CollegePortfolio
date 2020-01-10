from Board import Board
from InputField import InputField

#Leopold Marx, Quoc Than, Gavin Broussard
#stored important information about the board
class Controller():
    def __init__(self, window):
        self.window = window

        self.selected = 0,0