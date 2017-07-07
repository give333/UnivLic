let rec apl n f a = match n with
    0 -> a
  | _ -> apl (n-1) f (f a);;


let apl1 f a = apl 1 f a;;

let apl30mais1 a = apl 30 (function x -> x+1) a;;

apl30mais1 2;;

let rec filter f l = match l with
    [] -> []
  | h::t -> if ( f h) then h::(filter f t) else filter f t;;
filter (function x -> x mod 2 = 0) [1;2;3;4;5;6];;

let rec append lx ly = match lx with
    h::t -> h::append t ly
  | [] -> ly;;

append [1;2;3] [4;5;6];;

let rec membro l a = match l with
    [] -> false
  | h::t -> if (h = a) then true else membro t a;;

membro [1;2;3] 3;;
