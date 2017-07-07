import random #importa o modulo random


def jogada_par(tab, p1, p2, player1, player2, f):
	jogada = ""
	try: #a funçao vai tentar pedir ao utilizador um numero interiro, se isto acontecer o jogo continua, senao avanca para o ValueError
		jogada = int(input('Escolhe a casa que pretendes jogar (utiliza as teclas de 1 a 6): '))
	except ValueError: #evita que o jogo seja burlando a dar erro
		print('Introduza um valor de 1 a 6: ')
		jogada_par(tab, p1, p2, player1, player2, f)
	else:
		if jogada >= 1 and jogada <= 6: #verifica se o numero dado esta presente no intervalo [1,6]


			while p1<25 and p2<25:
				temp = jogada - 1 #uma vez que as listas comecam no 0 queremos que o input 1 corresponda ao elemento 0 da lista e assim sucessivamente
				tempReverse = 0 #momento da mudanca de lista, corresponde ao primeiro elemnto da lista 1
				if tab[0][temp] == 0: #anula a hipotese de jogar numa casa com o numero zero
					print('Esta casa tem o valor zero, por isso nao esta disponivel ')
					jogada_par(tab, p1, p2, player1, player2, f)
				for i in range(tab[0][temp]): 
					if ((temp -1) >= 0) : 
						tab[0][temp-1] = tab[0][temp-1]+1 #adiciona +1 a posicao anterior a jogada
						temp = temp - 1 #atribui um novo valor a variavel temp para que o jogo possa continuar nas outras casas
					else: #quando (temp-1)<0 e necessario mudar de linha
						tab[1][tempReverse]=tab[1][tempReverse]+1 # adiciona +1 ao primeiro elemento da segunda linha
						tempReverse = tempReverse + 1 #atribui um novo valor a variavel tempReverse				
				tab[0][jogada-1] = 0 #garante que a casa que o jogador escolheu fica igual a zero no fim do turno
				continua_impar(tab, p1, p2, player1, player2, f)
		else:
				print('Por favor jogue uma casa valida...')
				jogada_par(tab, p1, p2, player1, player2, f)


def jogada_impar(tab, p1, p2, player1, player2, f):
	jogada = ""
	try:

		jogada = int(input('Escolhe a casa que pretendes jogar (utiliza as teclas de 1 a 6): '))
	except ValueError:
		print('Introduza um valor de 1 a 6: ')
		jogada_impar(tab, p1, p2, player1, player2, f)
	else:
		if jogada >= 1 and jogada <= 6:


			while p1<25 and p2<25:
				temp = jogada - 1 #uma vez que as listas comecam no 0 queremos que o input 1 corresponda ao elemento 0 da lista e assim sucessivamente
				tempReverse = 5 #momento da mudanca de lista, corresponde ao primeiro elemnto da lista 2
				if tab[1][temp] == 0:
					print('Esta casa tem o valor zero, por isso nao esta disponivel ')
					jogada_impar(tab, p1, p2, player1, player2, f)
				for i in range(tab[1][temp]):
					if ((temp) <= 5) :
						tab[1][temp] = tab[1][temp]+1
						temp = temp + 1
					else:
						tab[0][tempReverse]=tab[0][tempReverse]+1
						tempReverse = tempReverse - 1				
				tab[1][jogada-1] = 0 
				continua_par(tab, p1, p2, player1, player2, f)

		else:
			print('Por favor jogue uma casa valida...')
			jogada_impar(tab, p1, p2, player1, player2, f)

		continua_par(tab, p1, p2, player1, player2, f)

def continua_par(tab, p1, p2, player1, player2, f):
	print('------------ ', player1 , p1,' ------------')
	print ('	', tab[0], "\n",'	', tab[1])
	print('------------ ', player2, p2,' ------------')
	print('E a tua vez de jogar', f)
	jogada_par(tab, p1, p2, player1, player2, f)


def continua_impar(tab, p1, p2, player1, player2, f):
	print('------------ ', player1 , p1,' ------------')
	print ('	', tab[0], "\n",'	', tab[1])
	print('------------ ', player2, p2,' ------------')
	print('E a tua vez de jogar', player2)
	jogada_impar(tab, p1, p2, player1, player2, f)

def inicio_jogo(tab, p1, p2, player1, player2, f):
	print('------------ ', player1 , p1,' ------------')
	print ('	', tab[0], "\n",'	', tab[1])
	print('------------ ', player2, p2,' ------------')
	print('E a tua vez de jogar', f)
	jogada_par(tab, p1, p2, player1, player2, f)
	

def pjogada(player1, player2, tab, p1, p2):
	f = random.choice([player1, player2]) #variavel para escolher quem joga primeiro
	if f == player1: #se a escolha "aleatoria" corresponder a variavel jogador1, o jogo decorre como se nao tivesse existido uma escolha aleatoria
		inicio_jogo(tab, p1, p2, player1, player2, f)
	else: #caso contrario os papeis de jogador2 e jogador1 invertem-se para evitar problemas durante os turnos
		inicio_jogo(tab, p1, p2, player2, player1, f)

def main(): #funcao que guarda as variaveis mais importantes
	tab = [[4,4,4,4,4,4], [4,4,4,4,4,4]]
	p1 = 0
	p2 = 0
	texto(tab, p1, p2)

def texto(tab, p1, p2):
	player1 = input('Qual é o seu nome Jogador 1?: ')
	player2 = input('Qual é o seu nome Jogador 2?: ')
	if player1 == player2: #garante que ambos os jogadores tem nomes diferentes
		print('Os nomes nao podem ser iguais, por favor escolham novos nomes')
		texto(tab, p1, p2)
	
	pjogada(player1, player2, tab, p1, p2)
	
def intro(c):
	print('----------------------------Bem Vindo ao Ouri!---------------------------')
	print('O objectivo do jogo é recolher mais pecas que o adversario. Vence o jogador que tiver 25 pecas ou mais.')
	print('-------------------------------------------------------------------------')
	if c == '1':
		main()	
	if c == '2':
		return
	if c == '3':
		return

def menu():
	print('0 - Sair')
	print('1 - Humano vs Humano: modo texto')
	print('2 - Humano vs Humano: modo gráfico')
	print('3 - Humano vs Computador')
	c = input('Escolha a opção desejada: ')
	if c == '0':
		return
	if c == '1':
		intro(c)
	else:
		print('Opcao invalida, por favor escolha uma opcao:')
		menu()



def loop(): 
	menu()
	i = int(input())
	if (i == 0):
		return 
	return

if __name__ == "__main__":
	loop()

menu() 
