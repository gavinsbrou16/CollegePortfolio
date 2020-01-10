from tkinter import Canvas, BOTH, TOP

from math import floor

from Game import Game

from Node import Node

#Leopold Marx, Quoc Than, Gavin Broussard
#Board handles anything related to editing, drawing, and storing the board
class Board():

    def __init__(self, window, dim, margin):
        self.nuteralrowcolor = "lightgrey"
        self.boldrowcolor = "black"
        self.selectedcolor = "red"

        self.originalcolor = "black"
        self.editcolor = "grey"
        self.wrongcolor = "red"

        self.window = window
        self.margin = margin
        self.dim = dim
        self.side = (dim - margin*2)/9

        self.original = Game.getGame().original
        self.solution = Game.getGame().solution
        self.cells = [[self.original[i][j] for j in range(9)] for i in range(9)]

        self.canvas = Canvas(self.window.main,width=self.dim,
                             height=self.dim-self.margin/2)
        self.canvas.pack(fill=BOTH, side=TOP)

        self.drawboard()

        self.canvas.bind("<Button-1>", self.setSelected)
        self.window.master.bind("<Key>", self.keyboardInput)

        self.stack = []

    #drawboard erases the canvas and redraws all the componets of the board
    def drawboard(self):
        #erase canvas
        self.canvas.delete("all")

        #draws grey rectangles for the selection

        x, y = self.window.controller.selected
        self.canvas.create_rectangle(self.margin, self.margin+self.side*y,
                                     self.dim - self.margin, self.margin+self.side*(y+1), fill="grey95")
        self.canvas.create_rectangle(self.margin + self.side * x, self.margin,
                                     self.margin + self.side * (x + 1), self.dim - self.margin, fill="grey95")
        self.canvas.create_rectangle(self.margin + self.side * (int)(x / 3)*3,
                                     self.margin + self.side * (int)(y / 3)*3,
                                     self.margin + self.side * (1 + (int)(x / 3)) * 3,
                                     self.margin + self.side * (1 + (int)(y / 3)) * 3, fill="grey95")

        #highlights all cells with the same value as the selected cell
        for i in range(9):
            for j in range(9):
                if (self.cells[i][j] == self.cells[x][y] and self.cells[i][j] != 0):
                    self.canvas.create_rectangle(self.margin + i * self.side, self.margin + self.side * j,
                                                 self.margin + (i + 1) * self.side, self.margin + self.side * (j + 1),
                                                 outline="grey95", fill="grey95")

        #draws lines
        for i in range(10):
            if i % 3 == 0:
                color = self.boldrowcolor
            else:
                color = self.nuteralrowcolor


            self.canvas.create_line(self.margin + i * self.side,
                                    self.margin,
                                    self.margin + i * self.side,
                                    self.dim - self.margin,
                                    fill=color)

            self.canvas.create_line(self.margin,
                                    self.margin + i * self.side,
                                    self.dim - self.margin,
                                    self.margin + i * self.side,
                                    fill=color)

        #draws the numbers
        for i in range(9):
            for j in range(9):
                if self.cells[i][j] != 0:
                    self.writecell(i, j, self.cells[i][j])

        #draws selection box
        self.selectcell(x,y)

    #checks if there are nine counts of a value on the board
    def nineOfVal(self, val):
        count = 0
        for i in range(9):
            for j in range(9):
                if self.cells[i][j] == val:
                    count += 1;
                    if count == 9:
                        return True
        return False

    #gets cell value for given x and y
    def getVal(self,x,y):
        return self.cells[x][y]

    #handles what happens when undo is pressed
    def UndoCellVal(self,val):
        x, y = self.window.controller.selected

        self.cells[x][y] = val

        self.drawboard()

    # each time a new value is inputed, we create a node that stores the previous value and append it to the list
    def changeCellVal(self, val):
        x, y = self.window.controller.selected

        if self.original[x][y] == 0:
            #before changing cell(x,y) value we want to create a reference to last value of that node
            node = Node(self.getVal(x,y),x,y)
            #appending node to stack
            self.stack.append(node)
            # changes to new value below
            self.cells[x][y] = val

            self.drawboard()
            self.window.inputfield.drawinput()

    #draws number into a cell
    def writecell(self, x, y, val):
        if self.solution[x][y] != val:
            color = self.wrongcolor
        elif self.original[x][y] == 0:
            color = self.editcolor
        else:
            color = self.originalcolor

        i = self.margin + x * self.side + self.side / 2
        j = self.margin + y * self.side + self.side / 2
        self.canvas.create_text(i, j, text=val, font="Saris 26 bold", tags="numbers", fill=color)

    #executed when someone clicks the canvas
    def setSelected(self, event):
        x, y = event.x, event.y
        if (self.margin < x < self.dim - self.margin and self.margin < y < self.dim - self.margin):
            x, y = floor((x - self.margin) / self.side), floor((y - self.margin) / self.side)
            self.window.controller.selected = x,y
            self.drawboard()

    #executed when someone types
    def keyboardInput(self, event):
        if event.char in "1234567890":
            self.changeCellVal(int(event.char))
        elif event.char == '\x7f':
            self.changeCellVal(0)

    #draws the red box around the selected cell
    def selectcell(self,x, y):
        self.canvas.create_line(self.margin + x * self.side,
                                self.margin + y * self.side,
                                self.margin + (x + 1) * self.side,
                                self.margin + y * self.side,
                                fill = self.selectedcolor)

        self.canvas.create_line(self.margin + x * self.side,
                                self.margin + (y + 1) * self.side,
                                self.margin + (x + 1) * self.side,
                                self.margin + (y + 1) * self.side,
                                fill=self.selectedcolor)

        self.canvas.create_line(self.margin + x * self.side,
                                self.margin + y * self.side,
                                self.margin + x * self.side,
                                self.margin + (y + 1) * self.side,
                                fill=self.selectedcolor)

        self.canvas.create_line(self.margin + (x + 1) * self.side,
                                self.margin + y * self.side,
                                self.margin + (x + 1) * self.side,
                                self.margin + (y + 1) * self.side,
                                fill=self.selectedcolor)
