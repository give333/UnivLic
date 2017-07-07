%%% listing. %%%%%%% trace. %%%%% nodebug. %%%%%
num(z).
num(s(X)):- num(X).
%%%%%%aula pratica 14/10/16
%%%%1

mais1(A,B):-
	soma(A,s(z),B).

soma(z,X,X).
soma(s(X),Y,s(Z)) :- soma(X,Y,Z).

sub(A,B,X):- soma(X,B,A).

le(z,_).
le(s(A),s(B)):- le(A,B).
le(A,B):- soma(A,_,B).

lt(z, s(_)).
lt(s(A), s(B)):- lt(A,B).

mult(z,_,z).
mult(s(A),B,X):-
	mult(A,B,Y),
	soma(B,Y,X).


dobro(X,Y):- mult(X, s(s(z)), Y).


div(A,B,X):- mult(X,B,A).
div(A,B,Q,R):-
	mult(B,Q,X),
	soma(X,R,A),
	lt(R,B).
%%%%%2 melhorias

pot(X,z,s(z)):- num(X).
pot(X,s(Y),Z):-
	pot(X,Y,W),
	mult(X,W,Z).

quadrado(X,Y):- pot(X, s(s(z)), Y).


%%%%%%%%%%%%%%%%%%%%%%%
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
	insort(X,SI,SX),
	isort(Xs,SX,SO).
	

