%	Para	descrever	um problema	deve	descrever:
%	estado_inicial([Condicoes])
%	estado_final	([Condicoes]).
%	e	declarar	as	accoes	que	permitem	modelar	o	seu	problema	com	o	seguinte	predicado:
%	accao(a1,Precond,AddList,DeleteList).
%/*	Exemplo	para	o	problema:	blocos	a,	b,	c	*/
accao(move_chao(A),[livre(A),	sobre(A,B)],	[chao(A),	livre(B),livre(A)],	[sobre(A,B)]):- member(A,[a,b,c]), member(B,[a,b,c]),	A\=	B.
accao(move(A,B),[livre(A),	livre(B),chao(A)],	[sobre(A,B), livre(A)],	[livre(B),	chao(A)]):- member(A,[a,b,c]), member(B,[a,b,c]),	A\=	B.
accao(move(A,B),[livre(A),	livre(B),sobre(A,C)],[sobre(A,B), livre(A),	livre(C)],	[livre(B),	sobre(A,C)]):- member(A,[a,b,c]), member(B,[a,b,c]), A\=B,member(C,[a,b,c]),A\=C,B\=C.
estado_final([chao(a),chao(c),	sobre(b,c),	livre(b),	livre(a)]).
%estado_final([chao(c),	livre(a),	sobre(b,c),sobre(a,b)]).
estado_inicial([chao(a),chao(c),	sobre(b,a),	livre(b),	livre(c)]).


h(chao(a),s0).
h(chao(c),s0).
h(sobre(b,a),s0).
h( livre(b),s0).
h( livre(c),s0).


%consq
h(chao(A),r(move_chao(A),S)):- h(livre(A),S),	h(sobre(A,B),S).
h(livre(B),r(move_chao(A),S)):- h(livre(A),S),	h(sobre(A,B),S).
h(livre(A),r(move_chao(A),S)):- h(livre(A),S),	h(sobre(A,B),S).
h(sobre(A,B),r(move(A,B),S)):- h(livre(A), S), h(livre(B),S), h(chao(A),S).
h(livre(A),r(move(A,B),S)):- h(livre(A), S), h(livre(B),S), h(chao(A),S).
h(sobre(A,B),r(move(A,B),S)):- h(livre(A), S), h(livre(B),S), h(sobre(A,C),S).
h(livre(A),r(move(A,B),S)):- h(livre(A), S), h(livre(B),S), h(sobre(A,C),S).
h(livre(B),r(move(A,B),S)):- h(livre(A), S),h(livre(B),S), h(sobre(A,C),S).


%inércia\
h(sobre(A,B),r(Ac,S)):- h(sobre(A,B),S),Ac\=move_chao(A), h(livre(A),S), h(sobre(A,B),S).
h(sobre(A,C),r(Ac,S)):- h(sobre(A,C),S), Ac\=move(A,B), h(livre(A), S), h(livre(B),S), h(sobre(A,C),S).
h(livre(A), h(chao(A):- h(chao(a),S), h(chao(c),S),	h(sobre(b,c),S), h(livre(b),S), h(livre(a),S).
%? S=	r(move(b,c),s0)


%/*	Exemplo	para	o	problema:	Calcar	sapatos	e	meias
accao(calcarSapato(Pe),[meia(Pe)],[sapato(Pe)],[]).
accao(calcarMeia(Pe),[],[meia(Pe)],[]).
estado_inicial(	[]).
estado_final(	[sapato(esq),sapato(dir)]).
%Cálculo	de	Situações
%estado	inicial
%	h(sapato(esq),s0).
%	consequências	positivas	das	ações
h(sapato(Pe),	r(calcarSapato(Pe),S)):-h(meia(Pe),S).
h(meia(Pe),r(calcarMeia(Pe),S)).

%regras	de	inércia
h(meia(Pe),r(_,S):- h(meia(Pe),S).
h(sapato(Pe),r(_,S):- h(sapato(Pe),S).
%*/
%	Exemplo	para	o	problema	de	ir	as	compras	(esta	descrito	no	livro):	
%	Problema	das	Compras

accao(go(X,Y),[at(X)],	[at(Y)],[at(X)]):- (X=super;X=hws;X=home), (Y=super;Y=hws;Y=home), X\=Y.
accao(buy(X),[sell(L,X),at(L)],[have(X)],[]):- estado_inicial(Li), member(sell(L,X),Li).

estado_inicial([at(home),sell(super,banana),sell(hws,drill),sell(super,milk)]).
estado_final([have(milk),have(drill),have(banana),at(home)]).
%estado	inicial

h(at(home),s0).
h(sell(super,banana),s0).
h(sell(hws,drill),s0).
h(sell(super,milk),s0).

%consq	positivas
h(have(X),r(buy(X),S):- h(sell(L,X),S),	h(at(L),S).
h(at(Y),	r(go(X,Y),S))	:- h(at(X),S).

%inércia
h(have(X),r(_,S))	:- h(have(X),S).
h(at(X),r(A,S))	:- h(at(X),S),	A\=	go(X,_).
