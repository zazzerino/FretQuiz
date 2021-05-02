import React from "react";
import Button from "@material-ui/core/Button";
import { useHistory } from "react-router";
import { sendJoinGame } from "../../websocket/game";

export interface JoinGameButtonProps {
  gameId: string,
  userId: string
}

export function JoinGameButton(props: JoinGameButtonProps) {
  const history = useHistory();

  return (
    <Button
      variant="contained"
      color="secondary"
      onClick={() => {
        sendJoinGame(props.gameId, props.userId);
        history.push('/game');
      }}
    >
      Join Game
    </Button>
  );
}