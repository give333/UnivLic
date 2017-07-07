%% 04/11/16
%%  functor(TERM, FUNC, ARITY).
%% arg(POS, TERM, ARG).

t(1, isto(nao, e(um), termo(simples)).
t(2, pois(pois(pois,claro), que(nao), se, percebe(o(que), significa(isto)))).






contem(T,T).
contem(T,TS	):-
	atom(T),
	functor(T,_,).

%%%%%%%%%%%%%%%
abp(no( _, L, R)) :- abp(L), abp(R).
abp(nil).

abp_member(X, no(X, _, _)).
abp_member(X, no(Y, L, _)) :-
	X < Y, !,
	abp_member(X, L).
abp_member(X, no(Y, _, R)) :-
	X > Y, !,
	abp_member(X, R).

abp_insert(X, nil, no(X, nil, nil)).
abp_insert(X, no(Y, L, R), no(Y, LL, R)):-
	X < Y, 
	abp_insert(X, L, LL).
abp_insert(X, no(Y, L, R), no(Y, L, RR)):-
	X > Y,
	abp_insert(X, R, RR).

abp_traverse(no(_, L, _), NO) :- abp_traverse(L, NO).
abp_traverse(no(V, _, _), V).
abp_traverse(no(_, _, R), NO):- abp_traverse(R, NO).

