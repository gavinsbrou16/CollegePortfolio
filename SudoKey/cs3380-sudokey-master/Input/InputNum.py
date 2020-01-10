from abc import ABC, abstractmethod

#Leopold Marx, Quoc Than, Gavin Broussard
# This is an abstract class for the input values, 1-9 and erase
class InputNum(ABC):

    @abstractmethod
    def click(self):
        pass