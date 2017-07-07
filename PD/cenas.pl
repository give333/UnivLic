mkq(N, Q) :-
 length(Q, N),
 q(Q, N).

q([], _).
q([Q|Qs], N) :-
 q1(1, N, Q),
 q(Qs, N).

q1(Q, N, Q) :- Q =< N.
q1(I, N, Q) :- I < N, I1 is I+1, q1(I1, N, Q).

% cidades

:- dynamic(ncities/1).
:- dynamic(d0/3).

mkprob(N, S) :-
 set_seed(S),
 retractall(ncities(_)),
 retractall(d0(_,_,_)),
 asserta(ncities(N)),
 populate(N).

c(X) :- ncities(N), fd_domain(X, 1, N).

d(A,B,D) :- d0(A,B,D).
d(A,B,D) :- d0(B,A,D).

populate(0) :- !.
populate(N) :-
 N > 0,
 N1 is N-1,
 populate(N1, N),
 populate(N1).

populate(0, _) :- !.
populate(N, N0) :-
 N0v2 is N0*3,
 random(1, N0v2, D),
 assertz(d0(N0, N, D)),
 N1 is N-1,
 populate(N1, N0).