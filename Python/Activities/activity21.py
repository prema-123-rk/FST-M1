import pytest

def add(a,b): return a+b
def sub(a,b): return a-b
def mul(a,b): return a*b
def div(a,b): return a/b

def test_add():
    assert add(10,5)==15

def test_sub():
    assert sub(10,5)==5

def test_mul():
    assert mul(10,5)==50

def test_div():
    assert div(10,5)==2
