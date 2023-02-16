import { DarkTheme, DefaultTheme, NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { ColorSchemeName } from 'react-native';
import { Home } from '../screens/Home';

import { RootStackParamList } from '../../types';
import ModalScreen from '../screens/ModalScreen';
import NotFoundScreen from '../screens/NotFoundScreen';
import { SignIn } from '../screens/SignIn';
import { SignUp } from '../screens/SignUp';

export default function Navigation({ colorScheme }: { colorScheme: ColorSchemeName }) {
  return (
    <NavigationContainer
      theme={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <RootNavigator />
    </NavigationContainer>
  );
}

const Stack = createNativeStackNavigator<RootStackParamList>();

function RootNavigator() {
  return (
    <Stack.Navigator>
      <Stack.Screen name="Root" component={Home} options={{ headerShown: false }} />
      <Stack.Group
        screenOptions={{
          headerStyle: {
            backgroundColor: '#1A54F8',
          },
          headerTintColor: '#fff',
        }}
      >
        <Stack.Screen
          name="SignUp"
          component={SignUp}
          options={{
            title: 'Cadastro',
          }}
        />
        <Stack.Screen
          name="SignIn"
          component={SignIn}
          options={{
            title: 'Login',
          }}
        />
      </Stack.Group>
      <Stack.Screen name="NotFound" component={NotFoundScreen} options={{ title: 'Oops!' }} />
      <Stack.Group screenOptions={{ presentation: 'modal' }}>
        <Stack.Screen name="Modal" component={ModalScreen} />
      </Stack.Group>
    </Stack.Navigator>
  );
}
