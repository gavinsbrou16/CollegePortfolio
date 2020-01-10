import csv
import random

import numpy as np

#Leopold Marx, Quoc Than, Gavin Broussard
#gets game from csv file
class Game:
    __instance = None

    @staticmethod
    def getGame():
        if Game.__instance == None:
            Game()
        return Game.__instance

    def __init__(self):
        if Game.__instance != None:
            raise Exception("Class Game is a singleton")
        else:
            self.filename = "sudoku.csv"
            self.original, self.solution = self.importGames()
            Game.__instance = self

    def importGames(self):
        with open(self.filename) as f:
            reader = csv.reader(f)
            vals = random.choice(list(reader))
            for i in range(2):
                vals[i] = [int(char) for char in vals[i]]
                vals[i] = np.reshape(vals[i],(9,9)).tolist()
            return vals[0],vals[1]
