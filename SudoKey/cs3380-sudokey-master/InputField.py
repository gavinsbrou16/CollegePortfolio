from tkinter import Frame, BOTH, LEFT, Canvas, BOTTOM

from Hint import Hint
from Input.Erase import Erase
from Input.Num import Num
from Undo import Undo
import math
#Leopold Marx, Quoc Than, Gavin Broussard
#canvas for the input field at the bottom
class InputField():
    def __init__(self, window, dim, margin):
        self.window = window
        self.dim = dim
        self.margin = margin
        self.side = (dim - margin * 2) / 9
        self.canvas = Canvas(self.window.main,width=self.dim,
                             height=3*self.margin/2+ 2*self.side )
        self.canvas.pack(fill=BOTH, side=BOTTOM)
        self.nums = [Num(self, i+1) for i in range(9)]
        self.erase = Erase(self)
        self.undo = Undo(self)
        self.hint = Hint(self)
        self.drawinput()
        self.canvas.bind("<Button-1>", self.inputClick)

    #draws the input board
    def drawinput(self):
        self.canvas.delete("all")

        for i in range(9):
            if self.window.board.nineOfVal(i + 1):
                self.canvas.create_rectangle(self.margin + i * self.side, self.margin / 2,
                                             self.margin + (i + 1) * self.side, self.side +self.margin / 2, fill="grey85")

        self.canvas.create_line(self.margin ,
                                self.margin/2,
                                self.dim - self.margin,
                                self.margin / 2,
                                fill="black")
        self.canvas.create_line(self.margin ,
                                self.side + self.margin/2,
                                self.dim - self.margin,
                                self.side + self.margin/2,
                                fill="black")
        self.canvas.create_line(self.margin,
                                self.side*2 + self.margin / 2,
                                self.dim - self.margin,
                                self.side*2 + self.margin / 2,
                                fill="black")

        for i in range(10):
            self.canvas.create_line(self.margin + i * self.side,
                                    self.margin / 2,
                                    self.margin + i * self.side,
                                    self.margin/2 + self.side*(2 if i%3 ==0 else 1),
                                    fill="black")

            if i != 9:
                color = "grey" if self.window.board.nineOfVal(i+1) else "black"
                self.canvas.create_text(self.margin + i * self.side + self.side / 2,
                                        self.side/2 + self.margin/2,
                                        text=i+1, font="Saris 26 bold", tags="numbers", fill=color)

        self.canvas.create_text(self.margin + 1 * self.side + self.side / 2,
                                3*self.side / 2 + self.margin / 2,
                                text="ERASE", font="Saris 26 bold", tags="text", fill="black")
        self.canvas.create_text(self.margin + 4 * self.side + self.side / 2,
                                3*self.side / 2 + self.margin / 2,
                                text="UNDO", font="Saris 26 bold", tags="text", fill="black")
        self.canvas.create_text(self.margin + 7 * self.side + self.side / 2,
                                3*self.side / 2 + self.margin / 2,
                                text="HINT", font="Saris 26 bold", tags="text", fill="black")

    #handels what happens when someone clicks the input canvas
    def inputClick(self, event):
        x, y = event.x, event.y
        if self.margin < x < self.dim - self.margin and self.margin/2 < y < self.side + self.margin/2:
            self.nums[(int)((x-self.margin)/self.side)].click()
        elif self.side + self.margin/2 < y < 2*self.side + self.margin/2:
            if self.margin < x < self.margin + self.side*3:
                self.erase.click()
            elif self.margin + self.side*3 < x < self.margin + self.side*6:
                self.undo.click()
            elif self.margin + self.side*6 < x < self.dim - self.margin:
                self.hint.click()

