r = 3
c = 5
min_i = 1
max_c = 6
min_slices = [{'i': 2, 'j': 1}, {'i': 1, 'j': 2}]
max_slices = [{'i': 6, 'j': 1}, {'i': 3, 'j': 2}, {'i': 2, 'j': 3}, {'i': 1, 'j': 6}, {'i': 3, 'j': 1}]
pizza = [[0, 0, 0, 0, 0], [0, 1, 1, 1, 0], [0, 0, 0, 0, 0]]
pizza_sliced = [[0, 1, 2, 3, 0], [0, 1, 2, 3, 0], [0, 0, 0, 0, 0]]
expd = []

for i in range(r):
	for j in range(c):
		if not pizza_sliced[i][j]:
