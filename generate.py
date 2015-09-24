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

def randindex():
	return '14%04d' % random.randint(0, 9999)


def randstring(size = 10, alphanum = False, uppercase = False):
	res = ''
	letters = list(alphabet)
	letters_uppercase = list(alphabet.upper())
	x = '0123456789'
	digits = list(x)
	for i in range(size):
		l = letters
		if uppercase:
			upper = random.random()
			if upper > .7:
				l = letters_uppercase
		if alphanum:
			alpha = random.random()
			if alpha > .8:
				res += random.choice(digits)
			else:
				res += random.choice(l)
		else:	
			res += random.choice(l)
		
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

def checkIn(n):
	for i in range(n):
		sepDate = '.'
		sepTime = ':'
		x = random.random()
		if x > 0.8:
			sepDate = '/'
		if x < .3:
			sepTime = '.'
		date1 = randDate(separator = sepDate)
		time1 = randTime(hourEnd = 12, separator = sepTime)
		time2 = randTime(hourStart = 13, hourEnd = 23, separator = sepTime)
		date2 = date1
		if x > 0.4 and x < 0.45:
			date2 = randDate(separator = sepDate)

		if x > 0.5 and x < 0.55:
			time1, time2 = time2, time1

		print "%s %s-%s %s-%s" % (random.choice(names), date1, time1, date2, time2)

def points(n):
	END = 48 * 60
	team = 0
	team1 = "Heat"
	team2 = "Thunder"
	current_team = team1
	print "%s %s" % (team1, team2)
	time = 0
	while True:
		player = random.randint(4, 15)
		atack = random.randint(5, 24)
		time += atack
		if time >= END:
			return
		ap = random.random() * 100
		if ap <= 20:
			attempt = 1
		elif ap > 20 and ap <= 70:
			attempt = 2
		elif ap > 70:
			attempt = 3

		if attempt == 1:
			accuracy = random.randint(60, 98)	
			shoot = random.randint(0, 100)
			basket = 0
			if shoot <= accuracy:
				basket = 1
			print "%d %s %d %d %d" % (time, current_team, player, basket, attempt)			
			
		if attempt == 2:
			accuracy = random.randint(20, 80)	

		if attempt == 3:
			accuracy = random.randint(10, 60)	

		shoot = random.randint(0, 100)
		basket = 0
		if shoot <= accuracy:
			basket = 1
		print "%d %s %d %d %d" % (time, current_team, player, basket, attempt)			
		if team == 1: 
			current_team = team1
			team = 0
		else: 
			current_team = team2
			team = 1

def randDate(dayStart = 1, dayEnd = 30, monthStart = 1, monthEnd = 12, year = 2015, separator = '.'):
	return "%02d%s%02d%s%d" % (random.randint(dayStart, dayEnd), separator, 
		random.randint(monthStart, monthEnd), separator, year)

def randTime(hourStart = 0, hourEnd = 23, minuteStart = 0, minuteEnd = 59, separator = ':'):
	return "%02d%s%02d" % (random.randint(hourStart, hourEnd), separator, random.randint(minuteStart, minuteEnd))

def p11(n):
	print n
	for i in range(n):
		print randstring(random.randint(2, 80), False, True)

def matrica(m, n):
	print "%d %d" % (m, n)
	for i in range(m):
		for j in range(n):
			print "%d\t" % random.randint(1, 100),
		print

def matrica_kv(m):
	print "%d" % m
	for i in range(m):
		for j in range(m):
			print "%d\t" % random.randint(1, 100),
		print

def p13(n):
	added = set()
	indexes = set()
	for i in range(n):
		name = random.choice(names)
		if name not in added:
			print '%s %s %d' % (name, randindex(), random.randint(5, 10))
			added.add(name)

def p21(n):
	print n
	for i in range(n):
		x = random.randint(1, 50)
		print x,
		for j in range(x):
			print random.randint(100, 1000000),
		print

def calls(n):
	prefixes = [70, 71, 72, 75, 76, 77, 78, 88, 99]
	type = "I"
	for i in range(n):
		x = random.random()
		if x > 0.8:
			separator = '.'
		else:
			separator = ':'
		if x > 0.5:
			type = "O"
		else:
			type = "I"
		if x > 0.9:
			print "0%s%03d %02d%s%02d%s%02d %s" % (str(random.choice(prefixes)), random.randint(0, 999), 
			random.randint(0, 2), separator, random.randint(0, 60), separator, random.randint(0, 60), type)
		else:
			print "0%s%06d %02d%s%02d%s%02d %s" % (str(random.choice(prefixes)), random.randint(0, 999999), 
			random.randint(0, 2), separator, random.randint(0, 60), separator, random.randint(0, 60), type)

def br(n):
	for i in range(n):
		print '%d %d %d' % (random.randint(1, 50), random.randint(1, 50), random.randint(1, 50))

if __name__ == "__main__":
	if len(sys.argv) <= 1:
		print 'Usage: %s [arguments]' % (sys.argv[0])
	else:
		n = int(sys.argv[1])
		points(n)

