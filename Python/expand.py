r = 3
c = 5
min_i = 1
max_c = 6
min_slices = [{'i': 2, 'j': 1}, {'i': 1, 'j': 2}]
pizza = [[0, 0, 0, 0, 0], [0, 1, 1, 1, 0], [0, 0, 0, 0, 0]]
pizza_sliced = [[0, 1, 2, 3, 0], [0, 1, 2, 3, 0], [0, 0, 0, 0, 0]]
expd = []

def get_size(i, j):
	val = pizza_sliced[i][j]
	l = 0
	k = 0
	while i + l < r and pizza_sliced[i + l][j] == val:
		l += 1
	while j + k < c and pizza_sliced[i][j + k] == val:
		k += 1
	return {'i': l, 'j': k}

def get_max_exp(i, j, pie):


for i in range(r):
	for j in range(c):
		val = pizza_sliced[i][j]
		if val:
			expd.append(val)
		pie = get_size(i, j)
