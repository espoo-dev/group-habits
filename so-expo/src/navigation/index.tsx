import { DarkTheme, DefaultTheme, NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { ColorSchemeName } from 'react-native';
import { Home } from '../screens/Home';

import { PrivateStackParamList, RootStackParamList } from '../../types';
import { useAuth } from '../hooks/useAuth';
import { Dashboard } from '../screens/Dashboard';
import ModalScreen from '../screens/ModalScreen';
import NotFoundScreen from '../screens/NotFoundScreen';
import { SignIn } from '../screens/SignIn';
import { SignUp } from '../screens/SignUp';

export default function Navigation({ colorScheme }: { colorScheme: ColorSchemeName }) {
  const { isSignedIn } = useAuth()

  return (
    <NavigationContainer
      theme={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      {
        isSignedIn ? <PrivateNavigator /> : <RootNavigator />
      }
    </NavigationContainer>
  );
}

const StackPublic = createNativeStackNavigator<RootStackParamList>();
const StackPrivate = createNativeStackNavigator<PrivateStackParamList>();

function RootNavigator() {
  return (
    <StackPublic.Navigator>
      <StackPublic.Screen name="Root" component={Home} options={{ headerShown: false }} />
      <StackPublic.Group
        screenOptions={{
          headerStyle: {
            backgroundColor: '#1A54F8',
          },
          headerTintColor: '#fff',
        }}
      >
        <StackPublic.Screen
          name="SignUp"
          component={SignUp}
          options={{
            title: 'Cadastro',
          }}
        />
        <StackPublic.Screen
          name="SignIn"
          component={SignIn}
          options={{
            title: 'Login',
          }}
        />
      </StackPublic.Group>
      <StackPublic.Screen name="NotFound" component={NotFoundScreen} options={{ title: 'Oops!' }} />
      <StackPublic.Group screenOptions={{ presentation: 'modal' }}>
        <StackPublic.Screen name="Modal" component={ModalScreen} />
      </StackPublic.Group>
    </StackPublic.Navigator>
  );
}

function PrivateNavigator() {
  return (
    <StackPrivate.Navigator>
      <StackPrivate.Screen name="Dashboard" component={Dashboard} options={{ headerShown: false }} />
      <StackPrivate.Screen name="NotFound" component={NotFoundScreen} options={{ title: 'Oops!' }} />
      <StackPrivate.Group screenOptions={{ presentation: 'modal' }}>
        <StackPrivate.Screen name="Modal" component={ModalScreen} />
      </StackPrivate.Group>
    </StackPrivate.Navigator>
  );
}
