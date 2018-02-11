# from json import loads
import json

data = json.load(open("data/connect.json"))

r = data['rows']
c = data['columns']
min_i = data['min']
max_c = data['maxCells']
# min_slices = data['shapes']
pizza = data['map']
pizza_sliced = data['pizzaSliced']
max_slices = data['shapes']

# r = 3
# c = 5
# min_i = 1
# max_c = 6
# min_slices = [{'i': 2, 'j': 1}, {'i': 1, 'j': 2}]
# max_slices = [{'i': 6, 'j': 1}, {'i': 3, 'j': 2}, {'i': 2, 'j': 3}, {'i': 1, 'j': 6}, {'i': 3, 'j': 1}]
# pizza = [[0, 0, 0, 0, 0], [0, 1, 1, 1, 0], [0, 0, 0, 0, 0]]
# pizza_sliced = [[0, 1, 2, 3, 0], [0, 1, 2, 3, 0], [0, 0, 0, 0, 0]]
expd = set()

def get_size(i, j):
	val = pizza_sliced[i][j]
	l = 0
	k = 0
	while i + l < r and pizza_sliced[i + l][j] == val:
		l += 1
	while j + k < c and pizza_sliced[i][j + k] == val:
		k += 1
	return {'i': l, 'j': k}

def could_parse(i, j, part, val):
	if i < 0 or j < 0 or i + part['i'] > r or j + part['j'] > c:
		return False
	l = i
	while l - i < part['i']:
		k = j
		while k - j < part['j']:
			if pizza_sliced[l][k] and pizza_sliced[l][k] != val:
				return False
			k += 1
		l += 1
	return True


def parse(l, k, part, val):
	i = l
	while i - l < part['i']:
		j = k
		while j - k < part['j']:
			pizza_sliced[i][j] = val
			j += 1
		i += 1


def srav(i, j, val, pie):
	for slice_part in max_slices:
		if pie['i'] <= slice_part['i'] and pie['j'] <= slice_part['j']:
			l = i - abs(pie['i'] - slice_part['i'])
			start_k = j - abs(pie['j'] - slice_part['j'])
			while l <= i:
				k = start_k
				while k <= j:
					if could_parse(l, k, slice_part, val):
						parse(l, k, slice_part, val)
						return
					k += 1
				l += 1

for i in range(r):
	for j in range(c):
		val = pizza_sliced[i][j]
		if val and val not in expd:
			expd.add(val)
			pie = get_size(i, j)
			srav(i, j, val, pie)

for i in range(r):
	for j in range(c):
		print(pizza_sliced[i][j], end=' ')
	print("")