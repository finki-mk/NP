import random
import sys
import string

alphabet = string.ascii_lowercase

names = ["Jacob", "Michael", "Joshua", "Matthew", "Ethan", "Andrew", "Daniel", 
		"Anthony", "Christopher", "Joseph", "William", "Alexander", "Ryan", "David", 
		"Nicholas", "Tyler", "James", "John", "Jonathan", "Nathan", "Emily", "Emma", 
		"Madison", "Abigail", "Olivia", "Isabella", "Hannah", "Samantha", "Ava", 
		"Ashley", "Sophia", "Elizabeth", "Alexis", "Grace", "Sarah", "Alyssa", "Mia", "Natalie", "Chloe", "Brianna"]

def getwords():
	words = open('NP-source/words.txt', 'r').readlines()
	return [w.strip() for w in words]


def randstring(size):
	res = ''
	letters = list(alphabet)
	for i in range(size):
		res += random.choice(letters)
	return res
def rn(n):
	print n
	for i in range(n):
		print '%s' % random.choice(names)
		for j in range(4):
			for k in range(5):
				print '%d' % random.randint(30, 60),
			print

def rec(n):
	print n
	words = getwords()
	for i in range(n):
		print random.choice(words)

def pbt(n):
	prefixes = [70, 71, 75, 76, 77, 78]
	print n
	for i in range(n):
		print "%s:0%s" % (random.choice(names), str(random.choice(prefixes)) + str(random.randint(0, 999999)))

	for i in range(n / 3):
		if i % 2 == 0:
			print "%s:%s" % ("NAME", random.choice(names))
		else:
			print "%s:%s" % ("NUM", random.randint(100, random.randint(999, 99999)))

def bc(n, size):
	print n, size
	x = set()
	generated = 0
	while generated < n:
		number = random.randint(1, 5000)
		if number not in x:
			print number
			x.add(number)
			generated += 1
	generated = 0
	x = set()
	while  generated < n:
		size = random.randint(3, 10)
		s = randstring(size)
		if s not in x:
			print s
			x.add(s)
			generated += 1

def students(n):
	for i in range(n):
		print "%s\t%d\t%d\t%d\t%d\t%d" % (random.choice(names), 
			random.randint(100000, 150000), 
			random.randint(0, 100), random.randint(0, 100),
			random.randint(0, 100), random.randint(0, 100))

if __name__ == "__main__":
	if len(sys.argv) <= 1:
		print 'Usage: %s [arguments]' % (sys.argv[0])
	else:
		n = int(sys.argv[1])
		rn(n)

