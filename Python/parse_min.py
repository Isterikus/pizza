# from json import loads
import json

data = json.load(open("data/connect.json"))
# data = '{"rows":3,"columns":5,"min":1,"maxCells":6,"map":[[0,0,0,0,0],[0,1,1,1,0],[0,0,0,0,0]],"shapes":[{"i":1,"j":2},{"i":2,"j":1}],"pizzaSliced":null}'
# data = loads(data)
# print(data)
r = data['rows']
c = data['columns']
min_i = data['min']
max_c = data['maxCells']
min_slices = data['shapes']
pizza = data['map']
pizza_sliced = [[0 for j in range(c)] for i in range(r)]
part = 1

def can_slice(i, j, slice):
	m = 0
	t = 0
	for l in range(slice['i']):
		for k in range(slice['j']):
			if (i + l >= r) or (j + k >= c):
				return False
			if (pizza_sliced[i + l][j + k]):
				return False
			if pizza[i + l][j + k] == 0:
				t += 1
			else:
				m += 1
	if t >= min_i and m >= min_i:
		return True
	else:
		return False

def slice_part(i, j, slice):
	global part
	for l in range(slice['i']):
		for k in range(slice['j']):
			pizza_sliced[i + l][j + k] = part
	part += 1

for i in range(r):
	for j in range(c):
		if not pizza_sliced[i][j]:
			for slice in min_slices:
				if can_slice(i, j, slice):
					slice_part(i, j, slice)

for i in range(r):
	for j in range(c):
		print(pizza_sliced[i][j], end=' ')
	print("")