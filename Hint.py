from random import randint

import numpy as np

from Node import Node

#Leopold Marx, Quoc Than, Gavin Broussard
#handles what happens when hint is clicked. randomly fills one empty cell
class Hint():
    def __init__(self, inputfield):
        self.inputfield = inputfield

    def click(self):

        board = self.inputfield.window.board
        numzeros = 81 - np.count_nonzero(board.cells)
        if numzeros > 0:
            random = randint(0,numzeros)
            for i in range(9):
                for j in range(9):
                    if board.cells[i][j] == 0:
                        if random ==  0:
                            self.inputfield.window.controller.selected = i,j
                            board.changeCellVal(board.solution[i][j])
                            # node = Node(board.cells[i][j], i, j)
                            # board.stack.append(node)
                            #
                            # board.cells[i][j] = board.solution[i][j]
                            # self.inputfield.window.controller.selected = i,j
                            # board.drawboard()
                            return
                        random -= 1;




