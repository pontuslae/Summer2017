-module(director).
-compile(export_all).


infinityserver() ->
	_ = server(),
	_ = infinityserver().

server() ->
    {ok, LSock} = gen_tcp:listen(9005, [binary, {packet, 0}, 
                                        {active, false}]),
    {ok, Sock} = gen_tcp:accept(LSock),
	ok = temp_send(Sock),
    {ok, Bin} = do_recv(Sock, []),
    ok = gen_tcp:close(Sock),
    Bin.
	
server(Port) ->
    {ok, LSock} = gen_tcp:listen(Port, [binary, {packet, 0}, 
                                        {active, false}]),
    {ok, Sock} = gen_tcp:accept(LSock),
    {ok, Bin} = do_recv(Sock, []),
    ok = gen_tcp:close(Sock),
    Sin = binary_to_list(Bin),
	Sin.

do_recv(Sock, Bs) ->
    case gen_tcp:recv(Sock, 0) of
        {ok, B} ->
            do_recv(Sock, [Bs, B]);
        {error, closed} ->
            {ok, list_to_binary(Bs)}
    end.
	
temp_send(Sock) ->
	gen_tcp:send(Sock, "localhost\n"),
	gen_tcp:send(Sock, "9006\n").