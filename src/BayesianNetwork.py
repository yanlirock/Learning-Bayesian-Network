"""This is Homework 4."""
import numpy as np

class IndependentBayesianNetwork:
    """Module to learn bayesian network with zero edges."""

    def learn_parameter(self, data):
        """Learn parameters for each variable"""
        print()

class ReadData:
    """Module to read data files."""

    def read_file(self, filename):
        """read the file into matrix"""
        dataset=np.loadtxt(filename,delimiter=',')
