append_dl(Xs-Ys, Ys-Zs, Xs-Zs).

flatten(Xs, Ys):-
	flatten_dl(Xs, Ys-[]).

flatten_dl([X|Xs], Ys-Zs):-
	flatten_dl(X, Ys-Ys1),
	flatten_dl(Xs, Ys1-Zs).

flatten_dl(X, [X|Xs]-Xs):-
	atomic(X), X \== [].

flatten_dl([], Xs-Xs).

inverte(Xs,Yx):- inverte_dl(Xs, Ys-[]).

inverte_dl([],Xs-Xs).
inverte_dl([X|Xs], Ys-Zs):-
	inverte_dl(Xs, Ys-[X|Zs]).

lookup(KEY, [KEY=VALUE|DICT], VALUE).
lookup(KEY, [KEY1=_|DICT], VALUE):-
	KEY \== KEY1, lookup(KEY, DICT, VALUE).

repeat(N) :- repeat(0, N).

repeat(N, N):- !, fail.
repeat(N, _).
repeat(N, M):- N1 is N+1, repeat(N1, M).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
lista([]).
lista([_|L]):- lista(L).

membro(X, [X|_]).
membro(X, [_|L]):- membro(X,L).

prefixo([],_).
prefixo([X|A], [X|B]):- prefixo(A,B).

sufixo(A,A).
sufixo(A, [_|B]):- sufixo(A,B).

sublista(S,L):- prefixo(S,L).
sublista(S, [_|L]):- sublista(S,L).

catena([], L, L).
catena([X|Xs], L, [X|Y]):- catena(Xs, L, Y).

nrev([],[]).
nrev([X|A], B):-
	nrev(A, AR),
	catena(AR, [X], B).

rev(L, R):- rev(L,[],R).
rev([],R,R).
rev([A|B],X,R):- rev(B, [A|X],R).

compr([],z).
compr([_|T],s(X)):- compr(T,X).

sel(E, [E|L],L).
sel(E, [X|L], [X|M]):- sel(E,L,M).


perm([],[]).
perm(L,[X|LP]):-
	sel(X,L,LX),
	perm(LX,LP).

ord([]).
ord([_]).
ord([A,B|X]):- A<B, ord([B|X]).

nsort(L,S):- perm(L,S), ord(S).

isort(I,S):- isort(I,[],S).

isort([],S,S).
isort([X|Xs],SI,SO):-
	insord(X,SI,SX),
	isort(Xs,SX,SO).

insord(X, [], [X]).
insord(X, [A|As], [X,A|As]):- X=<A.
insord(X, [A|As], [A|AAs]):-
	X>A,
	insord(X, As, AAs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	

%  somalista(Is, SOMA).

somalista([], 0).
somalista([I|Is], S):-
	somalista(Is, S0),
	S is I+S0.

qsort(L, S):- qsort(L, [], S).

qsort([], L, L).
qsort([X|Xs], L0, L):-
	particao(Xs, X, MEN, MAI),
	qsort(MAI, L0, L1),
	qsort(MEN, [X|L1], L).

particao([], _, [], []).
particao([X|L], Y, [X|L1], L2):-
	X =< Y, !,
	particao(L, Y, L1, L2).
particao([X|L], Y, L1, [X|L2]):-
	particao(L, Y, L1, L2).

psort(L, S):- perm(L,S), ord(S).


randomlist(0, []).
randomlist(N, [L|Ls]):-
	N>0, N1 is N-1,
	random(L),
	randomlist(N1, Ls).
	