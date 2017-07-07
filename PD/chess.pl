:- initialization(greetings).
:- initialization(play).
:- initialization(printab).

:- dynamic(pos/4).
:- dynamic(jogada/1).

%pos(PEÇA, COR, C, L)
pos("p", 0, 1, 2).
pos("p", 0, 2, 2).
pos("p", 0, 3, 2).
pos("p", 0, 4, 2).
pos("p", 0, 5, 2).
pos("p", 0, 6, 2).
pos("p", 0, 7, 2).
pos("p", 0, 8, 2).
pos("p", 1, 1, 7).
pos("p", 1, 2, 7).
pos("p", 1, 3, 7).
pos("p", 1, 4, 7).
pos("p", 1, 5, 7).
pos("p", 1, 6, 7).
pos("p", 1, 7, 7).
pos("p", 1, 8, 7).
pos("t", 0, 1, 1).
pos("t", 0, 8, 1).
pos("t", 1, 1, 8).
pos("t", 1, 8, 8).
pos("c", 0, 2, 1).
pos("c", 0, 7, 1).
pos("c", 1, 2, 8).
pos("c", 1, 7, 8).
pos("b", 0, 3, 1).
pos("b", 0, 6, 1).
pos("b", 1, 3, 8).
pos("b", 1, 6, 8).
pos("d", 0, 4, 1).
pos("d", 1, 4, 8).
pos("r", 0, 5, 1).
pos("r", 1, 5, 8).

pic("t", 0, "\33\[94m♜\33\[39m").
pic("c", 0, "\33\[94m♞\33\[39m").
pic("b", 0, "\33\[94m♝\33\[39m").
pic("d", 0, "\33\[94m♛\33\[39m").
pic("r", 0, "\33\[94m♚\33\[39m").
pic("p", 0, "\33\[94m♟\33\[39m").
pic("t", 1, "\33\[30m♜\33\[39m").
pic("c", 1, "\33\[30m♞\33\[39m").
pic("b", 1, "\33\[30m♝\33\[39m").
pic("d", 1, "\33\[30m♛\33\[39m").
pic("r", 1, "\33\[30m♚\33\[39m").
pic("p", 1, "\33\[30m♟\33\[39m").

jogada(0).

gets(S) :-
    get0(C),
    \+ checkeof(C),
    gets([], C, S).
gets(S, 10, S).                % 10 é o newline
gets(S, -1, S).                % -1 é o end-of-file
gets(I, C, [C|O]) :- get0(CC), gets(I, CC, O).

checkeof(C) :-
    C is -1.

abs(X, Y) :- X < 0 -> Y is -X; Y = X.

printab :-
    for(I, 1, 8),
        format("~n", _),
        write(I),
        for(J, 1, 8),
            %printpeca(Ia, J),
            printc(I, J),
    fail.
printab :-
    format("~n ", _),
    for(I, 49, 56),
        format(" ~c ", [I]),
    fail.
printab.

printc(I, J) :-
    M is I mod 2,
    (   M is 0 ->
        N is J mod 2,
        (   N is 0 ->
            (   \+ pos(_, _, I, J) ->
                format("\33\[100m   \33\[49m", _)
            ;   pos(P, _, I, J), format("\33\[100m ~c \33\[49m", P)
            )
        ;   (   \+ pos(_, _, I, J) ->
                format("\33\[107m   \33\[49m", _)
            ;   pos(P, _, I, J), format("\33\[107m ~c \33\[49m", P)
            )
        )
    ;   N is J mod 2,
        (   N is 0 ->
            (   \+ pos(_, _, I, J) ->
                format("\33\[107m   \33\[49m", _)
            ;   pos(P, _, I, J), format("\33\[107m ~c \33\[49m", P)
            )
        ;   (   \+ pos(P, _, I, J) ->
                format("\33\[100m   \33\[49m", _)
            ;   pos(P, _, I, J), format("\33\[100m ~c \33\[49m", P)
            )
        )
    ).

printpeca(I, J) :-
    pos(P, _, I, J), !,
    format(" ~c ", P).
printpeca(_I, _J) :-
    format("   ", _).

toggle :-
    jogada(J),
    (
        J == 0, retractall(jogada(_)), assertz(jogada(1))
    ;
        J == 1, retractall(jogada(_)), assertz(jogada(0))
    ).

play :-
    write('Your play: '),
    gets(X), !,
    handleplay(X),
    play.
play.

handleplay(X) :-
    nth(1, X, Oc),   % From column
    nth(2, X, Or),   % From row
    nth(3, X, Dc),   % To column
    nth(4, X, Dr),   % To row
    number_codes(Oi, [Oc]),
    number_codes(Oj, [Or]),
    number_codes(Di, [Dc]),
    number_codes(Dj, [Dr]),
    Oi > 0, Oi < 9, Oj > 0, Oj < 9, % play inside board
    Di > 0, Di < 9, Dj > 0, Dj < 9,
    pos(P, C, Oi, Oj),
    checkteams([Oi|Oj], [Di|Dj]),
    jogada(T), T == C,           % whites, then blacks, alternating
    validajogada(P, [Oi|Oj], [Di|Dj]),
    updateboard([Oi|Oj], [Di|Dj]),
    toggle.
handleplay(_X) :-
    invalidnote.

checkteams(_, [Di|Dj]) :-
    \+ pos(_, _, Di, Dj).
checkteams([Oi|Oj], [Di|Dj]) :-
    pos(_, C1, Oi, Oj),
    pos(_, C2, Di, Dj),
    \+ C1 == C2.

updateboard([Oi|Oj], [Di|Dj]) :-
    pos(P, C1, Oi, Oj),
    pos(E, C2, Di, Dj),
    retract(pos(P, C1, Oi, Oj)),
    retract(pos(E, C2, Di, Dj)),
    assertz(pos(P, C1, Di, Dj)),
    assertz(pos(E, C2, 0, 0)).
updateboard([Oi|Oj], [Di|Dj]) :-
    pos(P, C1, Oi, Oj),
    retract(pos(P, C1, Oi, Oj)),
    assertz(pos(P, C1, Di, Dj)).

validajogada(P, O, D) :-
    P == "c",
    validacavalo(O, D).
validajogada(P, O, D) :-
    P == "t",
    validatorre(O, D).
validajogada(P, O, D) :-
    P == "b",
    validabispo(O, D).
validajogada(P, O, D) :-
    P = "p",
    validapeao(O, D).
validajogada(P, O, D) :-
    P = "d",
    (validabispo(O, D); validatorre(O, D)).
validajogada(P, O, D) :-
    P = "r",
    validarei(O, D).

validacavalo([Oi|Oj], [Di|Dj]) :-
    (Di is Oi-2; Di is Oi+2),
    (Dj is Oj-1; Dj is Oj+1).
validacavalo([Oi|Oj], [Di|Dj]) :-
    (Di is Oi-1; Di is Oi+1),
    (Dj is Oj-2; Dj is Oj+2).

validatorre(C, C).  % Mesma coordenada, chegou ao destino com sucesso
validatorre([Oi|Oj], [Di|Dj]) :-
    Oi == Di,
    (Dj > Oj, N is Oj+1; Dj < Oj, N is Oj-1),
    (\+ pos(_, _, Oi, N); N == Dj), !,
    validatorre([Oi|N], [Di|Dj]).
validatorre([Oi|Oj], [Di|Dj]) :-
    Oj == Dj,
    (Di > Oi, N is Oi+1; Di < Oi, N is Oi-1),
    (\+ pos(_, _, N, Oj); N == Di), !,
    validatorre([N|Oj], [Di|Dj]).

validabispo(C, C).
validabispo([Oi|Oj], [Di|Dj]) :-
    (Oi < Di, Ni is Oi+1; Oi > Di, Ni is Oi-1),
    (Oj < Dj, Nj is Oj+1; Oj > Dj, Nj is Oj-1),
    (\+ pos(_, _, Ni, Nj); [Ni|Nj] == [Di|Dj]), !,
    validabispo([Ni|Nj], [Di|Dj]).

validapeao([Oi|Oj], [Di|Dj]) :-
    pos(_, C, Oi, Oj),
    C == 0,
    (Oj == 2, Di == Oi, (Dj is Oj+1; Dj is Oj+2); Di == Oi, Dj is Oj+1).
validapeao([Oi|Oj], [Di|Dj]) :-
    pos(_, C, Oi, Oj),
    C == 1,
    (Oj == 7, Di == Oi, (Dj is Oj-1; Dj is Oj-2); Di == Oi, Dj is Oj-1).
validapeao([Oi|Oj], [Di|Dj]) :-
    pos(_, C, Oi, Oj),
    pos(_, _, Di, Dj),
    C == 0,
    (Di is Oi-1; Di is Oi+1),
    Dj is Oj+1.
validapeao([Oi|Oj], [Di|Dj]) :-
    pos(_, C, Oi, Oj),
    pos(_, _, Di, Dj),
    C == 1,
    (Di is Oi-1; Di is Oi+1),
    Dj is Oj-1.

validarei([Oi|Oj], [Di|Dj]) :-
    [Oi|Oj] \= [Di|Dj],
    (Di is Oi-1; Di is Oi+1; Di == Oi),
    (Dj is Oj-1; Dj is Oj+1; Dj == Oj).

invalidnote :-
    write('Invalid move detected.\n').

greetings :-
    % HOW DO I BREAK LINES
    write('Welcome to the chess validator.\nI use postal notation. Plays should be like the following:\nYour play: 5254 5755\nI will keep asking for plays until you either make an invalid play or the EOF is detected (CTRL+D).\nLet us play then!\n\n').