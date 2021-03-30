import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import user from '../user/userSlice';
import game from '../game/gameSlice';

export const store = configureStore({
  reducer: {
    user,
    game,
  },
});

store.subscribe(() => console.log(store.getState()));

export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;
