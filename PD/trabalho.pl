/*		([	[(A,8),(B,8),(C,8),(D,8),(E,8),(F,8),(G,8),(H,8)],
			[(A,7),(B,7),(C,7),(D,7),(E,7),(F,7),(G,7),(H,7)],
			[(A,6),(B,6),(C,6),(D,6),(E,6),(F,6),(G,6),(H,6)],
			[(A,5),(B,5),(C,5),(D,5),(E,5),(F,5),(G,5),(H,5)],
			[(A,4),(B,4),(C,4),(D,4),(E,4),(F,4),(G,4),(H,4)],
			[(A,3),(B,3),(C,3),(D,3),(E,3),(F,3),(G,3),(H,3)],
			[(A,2),(B,2),(C,2),(D,2),(E,2),(F,2),(G,2),(H,2)],
			[(A,1),(B,1),(C,1),(D,1),(E,1),(F,1),(G,1),(H,1)]
			]).

*/

tabuleiro([
			[(tb),(hb),(bb),(kb),(qb),(bb),(hb),(tb)],
			[(pb),(pb),(pb),(pb),(pb),(pb),(pb),(pb)],
			[(A,6),(B,6),(C,6),(D,6),(E,6),(F,6),(G,6),(H,6)],
			[(A,5),(B,5),(C,5),(D,5),(E,5),(F,5),(G,5),(H,5)],
			[(A,4),(B,4),(C,4),(D,4),(E,4),(F,4),(G,4),(H,4)],
			[(A,3),(B,3),(C,3),(D,3),(E,3),(F,3),(G,3),(H,3)],
			[(pw),(pw),(pw),(pw),(pw),(pw),(pw),(pw)],
			[(tw),(hw),(bw),(kw),(qw),(bw),(hw),(tw)]
			]).

%%:-dynamyc graveyard/2.
%%%%%posicao pecas pretas
posicao_inicial('tb','A', 1).
posicao_inicial('hb','B', 1).
posicao_inicial('bb','C', 1).
posicao_inicial('kb','D', 1).
posicao_inicial('qb','E', 1).
posicao_inicial('bb','F', 1).
posicao_inicial('hb','G', 1).
posicao_inicial('tb','H', 1).

%%%% peao
posicao_inicial('pb','A',2).
posicao_inicial('pb','B',2).
posicao_inicial('pb','C',2).
posicao_inicial('pb','D',2).
posicao_inicial('pb','E',2).
posicao_inicial('pb','F',2).
posicao_inicial('pb','G',2).
posicao_inicial('pb','H',2).

%%%%%%%posicao pecas brancas
posicao_inicial('tw','A', 8).
posicao_inicial('hw','B', 8).
posicao_inicial('bw','C', 8).
posicao_inicial('kw','D', 8).
posicao_inicial('qw','E', 8).
posicao_inicial('bw','F', 8).
posicao_inicial('hw','G', 8).
posicao_inicial('tw','H', 8).

%%%%peao
posicao_inicial('pw','A',7).
posicao_inicial('pw','B',7).
posicao_inicial('pw','C',7).
posicao_inicial('pw','D',7).
posicao_inicial('pw','E',7).
posicao_inicial('pw','F',7).
posicao_inicial('pw','G',7).
posicao_inicial('pw','H',7).


%%%%%%%%%%%%%%%%%%%%%%%inicializar jogo
comeca:-
	menu.

	%%%inputs
menu:-
	nl,write('###########################\n'),
     write(' ####  MENU PRINCIPAL ####\n'),
	   write('###########################\n\n'),
     write('      ### XADREZ ###\n\n'),	    
      write('    1 - Humano vs Humano\n'),
      write('    2 - Humano vs AI\n'),
      write('    0 - Sair\n\n'),
  write('Insira o numero correspondente a opcao:\n'),
  read(Opc), write(Opc),menuOpt(Opc).


menuOpt(1):-
  	nl,write('POCARL\n').
menuOpt(2):-
	nl,write('Nao tem\n'), menu.
%% 0 sair
menuOpt(0):-!. 


%%%algo diferente do menu
menuOpt(_):-
	nl, menu.
%%%%%%%%%%%%%%%%%%%%%%tipo de modo de jogo
jogar(Opc, nome(Opc)):-
	write('o caralho\n').
	
jogador(N, [nome(_)]).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Pedido dos Nomes dos JOGADORES
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
nome(1):-
	write(’########################'),nl,nl,
	write(’Quais os nomes dos jogadores?’),nl,nl,
	write(’Jogador 1:’),
	readln(Nome1),
	assert(jogador(1,Nome1)),nl,
	write(’Jogador 2:’),
	readln(Nome2),
	assert(jogador(2,Nome2)),nl,nl,
	jogador(1,[N1]),
	jogador(2,[N2]),
	write(N1),write(’, vai jogar com as pe¸cas brancas’),nl,nl,
	write(N2),write(’, vai jogar com as pe¸cas pretas’),nl,nl,
	write(’####################################’),nl,nl,
	write(’Boa Sorte!!’),nl,nl.
nome(X):-
(X=2;X=3),
write(’Qual o nome do jogador que se atreve a jogar contra n´os?’),nl,nl,
write(’Jogador:’),
readln(Nome1),
assert(jogador(1,Nome1)),
nl,
Nome=’Xana&tbs’,
assert(jogador(2,[Nome])).


%%%%%%%%%%%%%%%%%%
posicao_inicial(P,X,Y).
posicao_actual(P, X, Y, NP).


%%%%%%%%posicao actual(Peca, X, Y, NP).%%NP-> peca se encontra na coord XY
print_tabuleiro(T):-
	nl,print_linhas(T),nl.

/* para cada linha da matriz imprime todos os elementos dessa linha e passa à
   proxima até chegar ao fim da matriz*/
print_linhas([]).
print_linhas([L|Ls]) :-
	write('      | '),print_elementos(L),nl,nl, print_linhas(Ls).

% imprime todos os elementos de uma linha
print_elementos([]).
print_elementos([E|Es]):-
	( (\+ atomic(E), write('.'));(atomic(E), write(E)) ),
  write(' | '), print_elementos(Es).


 %%%%%%% mover
move_peca(X1,Y1,X2,Y2,V,Vnew):-
 	get(X1,Y1,P,V),set(X1,Y1,1,V,V1),set(X2,Y2,P,V1,Vnew).

move(X1,Y1,X2,Y2,D,Dnew):-get(X1,Y1,P,D),set(X1,Y1,1,D,D1),set(X2,Y2,P,D1,Dnew).


%%%%%%%movimentos pecas
%%%%%cavalo
salto_cavalo(X/Y,X1/Y1):-
	(movimento(Distx,Disty); movimento(Disty,Distx)),
	X1 is X+Distx,
	Y1 is Y+Disty,
	dentro_do_tabuleiro(X1),
	dentro_do_tabuleiro(Y1).
	movimento(2,1).
	movimento(-2,1).
	movimento(2,-1).
	movimento(-2,-1).

dentro_do_tabuleiro(Y):- 1 =< Y, Y =< 8. 