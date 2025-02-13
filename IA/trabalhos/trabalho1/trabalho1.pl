:-dynamic(foram_visitados/1). %regista os nos que foram visitados

%% Matriz 30x30
%estados(pos)
estado_inicial((18,18)).

estado_final((26,26)).


%bloqueios
bloqueio((1,2),(1,3)).
bloqueio((2,3),(2,2)).
bloqueio((3,4),(4,4)).
bloqueio((4,5),(3,5)).


%% Operacoes----------------

%subir
%op(EstadoAtual,nomeOp,EstadoSeg,Custo)
op((X,Y), sobe, (X,Z), 1):-
	Y<29,
	Z is Y+1,
	\+ bloqueio((X,Y),(X,Z)),
	nao_foi_visitado((X,Z)).

%direita
%op(EstadoAtual,nomeOp,EstadoSeg,Custo)
op((X,Y), direita, (Z,Y), 1):-
	X<29,
	Z is X+1,
	\+ bloqueio((X,Y),(Z,Y)),
	nao_foi_visitado((Z,Y)).

%descer
%op(EstadoAtual,nomeOp,EstadoSeg,Custo)
op((X,Y), desce, (X,Z), 1):-
	Y>1,
	Z is Y-1,
	\+ bloqueio((X,Y),(X,Z)),
	nao_foi_visitado((X,Z)).

%esquerda
%op(EstadoAtual,nomeOp,EstadoSeg,Custo)
op((X,Y), esquerda, (Z,Y), 1):-
	X>1,
	Z is X-1,
	\+ bloqueio((X,Y),(Z,Y)),
	nao_foi_visitado((Z,Y)).

%%%%%%%%%%%%%%

% verifica se a casa ja foi visitada, caso contrario adiciona
nao_foi_visitado(X):- 
	\+ foram_visitados(X),
	asserta(foram_visitados(X)).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%---------HEURISTICAS---------%%%%%%%%%%

% % HEURISTICA 1 - distancia de manhattan
	
h2((X,Y),Val):- estado_final(PosFinal), manhattan((X,Y),PosFinal,Val).

manhattan((X,Y), (Xf,Yf), Val):- 
	Xi is Xf - X,
	abs(Xi,Xi2),
	Yi is Yf - Y,
	abs(Yi,Yi2),
	Val is Xi2+Yi2.


% HEURISTICA 2 - distancia euclidiaana

h1((X,Y),Val):- estado_final(PosFinal), dist_euclidiana((X,Y),PosFinal,Val).

dist_euclidiana((X,Y),(Xf,Yf),Val):- 
	Xi is Xf - X,
	abs(Xi,Xi2),
	Yi is Yf - Y,
	abs(Yi,Yi2),
	Val is sqrt((Xi2*Xi2) + (Yi2*Yi2)).

% muda o sinal se for negativo, caso contraria nao altera
abs(X, Y) :- X > 0 -> Y is X; Y is -X.