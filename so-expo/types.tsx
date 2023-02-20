/**
 * Learn more about using TypeScript with React Navigation:
 * https://reactnavigation.org/docs/typescript/
 */

import { NavigatorScreenParams } from '@react-navigation/native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';

declare global {
  namespace ReactNavigation {
    interface RootParamList extends RootStackParamList { }
  }
}

export type RootStackParamList = {
  Root: NavigatorScreenParams<RootParamList> | undefined;
  SignIn: undefined;
  SignUp: undefined;
  Modal: undefined;
  NotFound: undefined;
};

export type PrivateStackParamList = {
  Dashboard: NavigatorScreenParams<RootParamList> | undefined;
  Modal: undefined;
  NotFound: undefined;
};

export type RootParamList = {
  Home: undefined;
};

export type RootStackScreenProps<Screen extends keyof RootStackParamList> = NativeStackScreenProps<
  RootStackParamList,
  Screen
>;
