import pytest

@pytest.fixture
def numbers():
    return list(range(11))

def test_sum(numbers):
    assert sum(numbers)==55
