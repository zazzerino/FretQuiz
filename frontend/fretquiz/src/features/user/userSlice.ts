import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../../app/store";

export const DEFAULT_ID = '';
export const DEFAULT_NAME = 'anon';

export interface User {
  id: string,
  name: string
}

const initialState: User = {
  id: DEFAULT_ID,
  name: DEFAULT_NAME
}

const userSlice = createSlice({
  name: 'user',

  initialState,

  reducers: {
    setUser: (_state, action: PayloadAction<User>) => action.payload,
  }
});

export const selectUser = (state: RootState) => state.user;

export const { setUser } = userSlice.actions;

export default userSlice.reducer;
