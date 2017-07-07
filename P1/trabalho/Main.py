import random
from random import randint
import sys

def menu():

    print("**********  J O G O  O U R I  **********")
    print("\n")
    print("0 - Sair") 
    print("1 - Humano vs Humano: modo texto")
    print("2 - Humano vs Humano: modo gráfico")
    print("3 - Humano vs Computador")
    print("\n")


def inicio(opcao):

    #descricao e objetivos do jogo \\ chama funcao pra introducao nome jogadores humanos

    print("\n")
    print("**************  J O G O  O U R I  ***************")
    print("\n")
    print("O Ouri e um jogo de captura. O Objetivo do jogo")
    print("e capturar mais sementes que o adversario. Vence")
    print("o jogador que obtiver 25 (ou mais) sementes.")
    print("\n")
    print("*************************************************")

    if opcao=="1":
        jogadores(2)
    if opcao=="2":
        return
    if opcao=="3":
        jogadores(1)


def jogadores(qt):

    #introducao do nome dos jogadores humanos \\computador reservado \\nao permite 2 nomes iguais

    jogador1=input("Digitar o nome do Jogador 1 ?: ")

    if jogador1.upper()== "COMPUTADOR":
        print("O nome COMPUTADOR esta reservado para a maquina..!\n")
        jogadores(qt)

    if qt==2:
        jogador2=input("Digitar o nome do Jogador 2 ?: ")
    else:
        jogador2="Computador"
        
    if jogador1.upper()==jogador2.upper() and qt==2:
        print("Os jogadores devem ter nomes diferentes! Escolher novos nomes..")
        jogadores(qt)
    
    jogador_iniciar(jogador1, jogador2)

def jogador_iniciar(jogador1, jogador2):

    #decidir aleatoriamente qual dos jogadores joga primeiro

    primeiro=random.choice([jogador1, jogador2])

    if primeiro == jogador1:
       segundo=jogador2
    else:
       segundo=jogador1

    main(primeiro,segundo)

def main(primeiro,segundo):

    #inicializacao tabuleiro com as sementes por casa e sementesem deposito
    x=0
    tabuleiro=[4,4,4,4,4,4,4,4,4,4,4,4,0,0]

    inicio_jogo(primeiro,segundo,tabuleiro)
    jogada_primeiro(tabuleiro,1,primeiro,segundo)
    
def jogada_primeiro(tabuleiro,jogador,primeiro,segundo):
    
    #jogada referente ao primeiro jogador 
    while(tabuleiro[12] < 25 or tabuleiro[13]<25):   

        if primeiro=="Computador":
            #jogada especifica para computador
            casa=jogada_computador(tabuleiro,jogador,primeiro,segundo)
            print(primeiro.upper()," selecionou a casa: ", casa, "\n")
        else:    
            print("Jogue por favor, "+primeiro.upper())
            print("(utilize as teclas de 1 a 6 para selecionar a casa que deseja): ", end="")
            casa=int(input())
            print("\n")

        indice=casa-1

        passa=verificar_jogada(tabuleiro,primeiro,jogador,indice,casa)

        #se nao passar na verificacao pedir nova jogada do mesmo jogador
        if passa==0:
            jogada_primeiro(tabuleiro,jogador,primeiro,segundo)
            
        #efetuar distribuicao das sementes
        jogar(tabuleiro, jogador,indice,primeiro,segundo)
        #verificar se o jogo verifica condicoes que o facam terminar
        verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo)
        #executar a jogada do segundo jogador
        jogada_segundo(tabuleiro,2,primeiro,segundo)

    #se numero sementes num deposito >25 chama fim do jogo
    fim_jogo(tabuleiro,primeiro,segundo)

def jogada_segundo(tabuleiro,jogador,primeiro,segundo):

    #jogada referente ao segundo jogador
    while(tabuleiro[12] < 25 and tabuleiro[13]<25):   

        if segundo=="Computador":
            #jogada especifica para computador
            casa=jogada_computador(tabuleiro,jogador,primeiro,segundo)
            print(segundo.upper()," selecionou a casa: ", casa, "\n")
        else:    
            print("Jogue por favor, "+segundo.upper())
            print("(utilize as teclas de 1 a 6 para selecionar a casa que deseja): ", end="")
            casa=int(input())
            print("\n")
            
        indice=casa+2*(6-casa)
        passa=verificar_jogada(tabuleiro,segundo,jogador,indice,casa)

        #se nao passar na verificacao pedir nova jogada do mesmo jogador
        if passa==0:
            jogada_segundo(tabuleiro,jogador,primeiro,segundo)

        #efetuar distribuicao das sementes
        jogar(tabuleiro, jogador,indice,primeiro, segundo)
        #verificar se o jogo verifica condicoes que o facam terminar
        verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo)
        #executar a jogada do segundo jogador
        jogada_primeiro(tabuleiro,1,primeiro,segundo)

    #se numero sementes num deposito >25 chama fim do jogo
    fim_jogo(tabuleiro,primeiro,segundo)
        
def jogada_computador(tabuleiro,jogador,primeiro,segundo):

    #verifica qual a melhor casa a escolher pelo computador
    tab_aux=[0,0,0,0,0,0]
    tab_aux_1=[0,0,0,0,0,0]
    passa=True
    ind_aux=0
    ind_aux_inicio=0
    volta=0
    total=0
    conta=0
    valor_inicio=0
    ind_passa=0
    randnovo=True
    rand1=0
    
    if jogador==1:
        for i in range(0,6):
            passa=True
            total=0
            if tabuleiro[i]==0: #verifica se a casa tem zero sementes
                tab_aux[i]=0
                passa=False
            elif tabuleiro[i]==1: #verifica se casa tem 1 semente e se outras casas tem + do que 1
                for j in range(0,6):
                    if tabuleiro[j]!=1 and tabuleiro[j]!=0:
                        passa=False

                if not passa:
                    tab_aux[i]=0
            
            ind_aux=tabuleiro[i]+i

            if ind_aux>11:
                ind_aux=ind_aux-12

            ind_aux_inicio=ind_aux
            
            if ind_aux>5 and ind_aux<12 and passa==True: #verifica se jogada fica no tabuleiro do adversario

                while(tabuleiro[ind_aux]==2 or tabuleiro[ind_aux]==1): #e se a casa tem 2 ou 3 sementes (depois de semear)
                   
                    if tabuleiro[i]>11:                        #e se passa + que uma vez pela casa partida
                        volta=1
                    
                    if volta==1:
                        #se passar + que uma vez pela casa partida tem que somar mais uma semente
                        total+=tabuleiro[ind_aux]+2
                        ind_aux-=2
                    else:
                        total+=tabuleiro[ind_aux]+1
                        ind_aux-=1

            if total==0:
                tab_aux[i]=0
            else:
                tab_aux[i]=total

    if jogador==2:
        for i in range(6,12):
            passa=True
            total=0
            if tabuleiro[i]==0: #verifica se a casa tem zero sementes
                tab_aux[i-6]=0
                passa=False
            elif tabuleiro[i]==1: #verifica se casa tem 1 semente e se outras casas tem + do que 1
                for j in range(6,12):
                    if tabuleiro[j]!=1 and tabuleiro[j]!=0:
                        passa=False

                if not passa:
                    tab_aux[i-6]=0
            
            ind_aux=tabuleiro[i]+i

            if ind_aux>11:
                ind_aux=ind_aux-12

            ind_aux_inicio=ind_aux

            if ind_aux>=0 and ind_aux<7 and passa==True: #verifica se jogada fica no tabuleiro do adversario

                while(tabuleiro[ind_aux]==2 or tabuleiro[ind_aux]==1): #e se a casa tem 2 ou 3 sementes (depois de semear)
                    
                    if tabuleiro[i]>11:
                        #e se passa + que uma vez pela casa partida
                        volta=1

                    if volta==1:
                        #se passar + que uma vez pela casa partida tem que somar mais uma semente
                        total+=tabuleiro[ind_aux]+1
                        ind_aux-=2
                    else:
                        total+=tabuleiro[ind_aux]+1
                        ind_aux-=1

            if total==0:
                tab_aux[i-6]=0
            else:
                tab_aux[i-6]=total
            
    
    tab_aux_1=tab_aux[:]            #tabela auxiliarpara registar capturas permitidas por cada hipotese de jogada do computador
    tab_aux_1.sort(reverse=True)    #ordenar descendentemente essa tabela, ficando a melhor(es) jogada(s) no inicio

    valor_inicio=tab_aux_1[0]
    
    #se se apurar apenas uma melhor jogada, essa e selecionada, senao retira-se aleatoriamente a melhor entre as que estiverem empatadas.
    #nao havendo nenhuma melhor, e obtida uma jogada aleatoria, sendo verificado severifica as condicoes para ser uma jogada valida
    while(i in range(1,6)):
        if tab_aux_1[i]==valor_inicio:
            conta+=1
        i+=1

    if conta==0 and valor_inicio!=0:
        for i in range(0,6):
            if tab_aux[i]==valor_inicio:
                if jogador==1:
                    ind_passa=i+1
                else:    
                    ind_passa=i+6-2*i
                    
                return ind_passa
    elif conta!=0 and valor_inicio!=0:
        
        ind_passa=randint(1,conta)
        conta=1
        while(i in range(0,6)):
            if tab_aux_1[i]==valor_inicio and conta==ind_passa:
                if jogador==1:
                    ind_passa=i+1
                else:    
                    ind_passa=i+6-2*i
                    
                return ind_passa
            conta+=1
    else:
        while(randnovo):

            rand1=randint(1,6)

            if jogador==1:
                ind_passa= rand1
            else:
                ind_passa=12-rand1+1
                
            if tabuleiro[ind_passa-1]!=0:
                randnovo=False

                if tabuleiro[ind_passa-1]==1:
                    for i in range(0,6):
                        if tabuleiro[i]!=1:
                            randnovo=True
                        
                        
        return rand1

def inicio_jogo(primeiro,segundo,tabuleiro):
    
    #imprimir tabuleiro inicial
    imprimir_tabuleiro(primeiro,segundo,tabuleiro)
    
    
def imprimir_tabuleiro(primeiro,segundo,tabuleiro):

    #defenicao do tabuleiro \\atencao a correspondencia entre casas e indices
    casas=[1,2,3,4,5,6]
  
    tam1=int((49-len(segundo))/2)
    tam2=int((49-len(primeiro))/2)

    print("\n")

    print(" "*tam1+segundo.upper())
    print(" "*tam1+"<"+"-"*(len(segundo)-1))
    print("_"*49)
    print("|     "*8+"|")
    print("|     "*8+"|")
    print("|     |",end="")
    for i in range(-3,-9,-1):
        print(" %2s" % tabuleiro[i],end="  |")
    print("     |")
    print("|     |"+"_____|"*6+"     |")
    print("|     |"+"      "*5+"     |     |")
    print("|     |"+"      "*5+"     |     |")
    print("| %2s" % tabuleiro[13]," |",end="")
    print("--",end="")
    for i in casas:
        if i==6:
            print(i,end="--")
        else:   
            print(i,end="-----")
    print("| %2s" % tabuleiro[12]," |")
    print("|     |"+"      "*5+"     |     |")
    print("|     |"+"_____ "*5+"_____|     |")
    print("|     "*8+"|")
    print("|     "*8+"|")
    print("|     |",end="")
    for i in range(0,6):
        print(" %2s" % tabuleiro[i],end="  |")
    print("     |")     
    print("|_____"*8+"|")
    print("\n")
    print(" "*tam2+"-"*(len(primeiro)-1)+">")
    print(" "*tam2+primeiro.upper())
    print("\n")
    

def verificar_jogada(tabuleiro, nome, jogador, indice,casa):

    #verificar se as jogadas estao de acordo com as regras
    passa=1
    soma=0

    #verifica se casa selecionada esta entre 1 e 7
    if casa <1 or casa >6:
        print(nome.upper()+", selecionar uma casa valida (utilizar as teclas de 1 a 6)!\n")
        passa=0
        
        return passa
    
    #verifica se a casa selecionada tem zero sementes, se for o caso pede nova jogada
    if tabuleiro[indice]==0:
        if nome.upper()!="COMPUTADOR":
            print(nome.upper()+", selecionar uma casa que contenha sementes!\n")
        passa=0
        return passa
    
    #verifica se a casa selecionada tem uma semente, e se existirem casa desse jogador
    #com mais do que uma semente, pedenova jogada
    if tabuleiro[indice]==1:
        if jogador==1:
            for i in range(0,6):
                if tabuleiro[i]!=1 and tabuleiro[i]!=0:
                    passa=0
        if jogador==2:
            for i in range(6,12):
                if tabuleiro[i]!=1 and tabuleiro[i]!=0:
                    passa=0
        if passa==0:
            if nome.upper()!="COMPUTADOR":
                print(nome.upper()+", nao pode tirar as sementes das casa que contenham apenas uma, ")
                print("enquanto houver casas com duas ou mais.Selecionar uma casa valida (utilizar as teclas de 1 a 6)!\n")

        return passa

        #Regra Suplementar1: Quando um jogador realiza um movimento e fica sem sementes, o adversario e obrigado a efetuar
        #                    uma jogada que introduza uma ou varias sementes do lado desse jogador.
        if jogador==1:
            if tabuleiro[indice]+indice not in range(6,12):
                for i in range(6,12):
                    soma+=tabuleiro[i]
                if soma==0:
                    passa=0
                    if nome.upper()!="COMPUTADOR":
                        print(nome.upper()+", e obrigatorio efetuar uma jogada em que introduza uma ou varias sementes ")
                        print("do lado do seu adversario(E1). Selecionar uma casa valida (utilizar as teclas de 1 a 6)!\n")
                    return passa

        if jogador==2:
            if tabuleiro[indice]+indice not in range(0,6):
                for i in range(0,6):
                    soma+=tabuleiro[i]
                if soma==0:
                    passa=0
                    if nome.upper()!="COMPUTADOR":
                        print(nome.upper()+", e obrigatorio efetuar uma jogada em que introduza uma ou varias sementes ")
                        print("do lado do seu adversario (E2). Selecionar uma casa valida (utilizar as teclas de 1 a 6)!\n")
                    return passa

                        
                    
def atualiza_deposito(tabuleiro,indice,jogador,primeiro,segundo):

    #atualiza os depositos, quando sementes na caso do adversario =3 ou =2, e retira tambem para o deposito as
    #sementes das casas imediatamente anteriores em que se continue a verificar que tem 2 ou 3 sementes
    total=0
    soma=0
    passa=1

    while(tabuleiro[indice]==3 or tabuleiro[indice]==2):
        total+=tabuleiro[indice]
        tabuleiro[indice]=0

        if indice==0:
            indice=11
        else:
            indice-=1

    if jogador==1:
        tabuleiro[12]+=total
    else:
        tabuleiro[13]+=total

    #Regra Suplementar2: Quando um jogador realiza uma captura e deixa o adversario sem sementes, e obrigado a jogar
    #                    novamente, de forma a introduzir uma ou varias sementes nas casas dele. 
    if jogador==1:
        for i in range(6,12):
            soma+=tabuleiro[i]

        if soma==0:
            imprimir_tabuleiro(primeiro,segundo,tabuleiro)
            print(primeiro.upper()+", e obrigatorio efetuar uma nova jogada em que introduza uma ou varias sementes ")
            print("do lado do seu adversario (E5). Selecionar uma casa valida (utilizar as teclas de 1 a 6)!\n")
            verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo)
            jogada_primeiro(tabuleiro,1,primeiro,segundo)
        else:
            passa=0
            return passa
    
    if jogador==2:
        for i in range(0,6):
            soma+=tabuleiro[i]
        if soma==0:
            imprimir_tabuleiro(primeiro,segundo,tabuleiro)
            print(segundo.upper()+", e obrigatorio efetuar uma nova jogada em que introduza uma ou varias sementes ")
            print("do lado do seu adversario (E6). Selecionar uma casa valida (utilizar as teclas de 1 a 6)!\n")
            verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo)
            jogada_segundo(tabuleiro,2,primeiro,segundo)
        else:
            passa=0
            return passa               
    
def jogar(tabuleiro, jogador,indice,primeiro, segundo):

    #distribuicao das sementes pelo tabuleiro
    total=1
    indice_passa=0
    salta=0
    entra=1
    passa=1
          
    while(total <=tabuleiro[indice]):

        if indice+total>11:
            indice_passa=indice+total-12
            if indice==indice_passa:
                salta=1
            if salta==1:
                tabuleiro[indice_passa+1]+=1
            else:
                tabuleiro[indice_passa]+=1

            
        else:
            indice_passa=indice+total
            tabuleiro[indice_passa]+=1

        total+=1
        
    tabuleiro[indice]=0

    #se a jogada efetuada nao termina numa casa do adversario, nao atualiza depositos
    if jogador==1:
        if indice_passa in range(0,6):
            entra=0
    if jogador==2:
        if indice_passa in range(6,12):
            entra=0

    if entra==1:
        if salta==1:
            if indice_passa>=11:
                indice_passa=indice_passa-12 #para corrigir o indice, quando chega ao fim da lista e tem que continuar no inicio
            passa=atualiza_deposito(tabuleiro,indice_passa+1,jogador,primeiro,segundo)
        else:
            if indice_passa>11:
                indice_passa=indice_passa-12
            passa=atualiza_deposito(tabuleiro,indice_passa,jogador,primeiro,segundo)

    imprimir_tabuleiro(primeiro,segundo,tabuleiro)
    
    if passa==0:
        if jogador==1:
            verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo)
            jogada_segundo(tabuleiro,2,primeiro,segundo)
        else:
            verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo)
            jogada_primeiro(tabuleiro,1,primeiro,segundo)
           
def verificar_fim_jogo(tabuleiro,jogador,primeiro,segundo):
    
    #verifica se estao reunidas as condicoes estraordinarias para se considerar que o jogo acabou
    soma2=0
    soma1=0
    conta1=0
    conta2=0
    sementes1=0
    sementes2=0
    indice1=0
    indice2=0
    fim=0
    
    for i in range(6,12):
        soma2+=tabuleiro[i]
        if tabuleiro[i]>0:
            conta2+=1
            sementes2=tabuleiro[i]
            indice2=i
                
    for i in range(0,6):
        soma1+=tabuleiro[i]
        if tabuleiro[i]>0:
            conta1+=1
            sementes1=tabuleiro[i]
            indice1=i

    if conta1==1 and conta2==1:
        if indice1+sementes1<indice2:
            fim=1
        else:
            fim=0

        if indice2+sementes2>11:
            if indice2+sementes2-12<indice1:
                fim=1
            else:
                fim=0
    if jogador==1:
        if soma2==0:
            while i in range(0,6):
                if (i + tabuleiro[i])> 5:
                    fim=0
                else:
                    fim=1
    else:
        if soma1==0:
            while i in range(6,12):
                if (i + tabuleiro[i])> 11:
                    fim=0

    if fim==1:
        tabuleiro[12]+=soma1
        tabuleiro[13]+=soma2        

        fim_jogo(tabuleiro,primeiro,segundo)
        
   
def fim_jogo(tabuleiro,primeiro,segundo):

    #informacao sobre quem ganhou o jogo
    if tabuleiro[12]==tabuleiro[13]:
        print("\nParabens ",primeiro.upper()," e ",segundo.upper(),".")
        print("O Jogo terminou empatado!!")
    elif tabuleiro[12]>tabuleiro[13]:
        print("\nParabens ",primeiro.upper())
        print("Foi o VENCEDOR do Jogo, tendo acumulado ",tabuleiro[12]," sementes, contra ",tabuleiro[13], " sementes acumuladas pelo ",segundo.upper(), ".")
    else:
        print("\nParabens ",segundo.upper())
        print("Foi o VENCEDOR do Jogo, tendo acumulado ",tabuleiro[13]," sementes, contra ",tabuleiro[12], " sementes acumuladas pelo ",primeiro.upper(), ".")
        
    sys.exit(0)
            
        
def loop():
    
    menu()
    opcao = input('Escolha a opção desejada: ')

    if opcao == "0":
        sys.exit(0)
    if opcao == "1" or opcao=="3":
        inicio(opcao)
    if opcao=="2":
        print("\nOpcao nao disponivel..(em desenvolvimento)\n")
        menu()
    else:
        print("\nSelecionar uma opcao valida, por favor:\n")
        menu()

        
if __name__=="__main__":
    loop()
