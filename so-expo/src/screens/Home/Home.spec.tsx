import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { fireEvent, render, screen } from '@testing-library/react-native';
import { RootStackParamList } from '../../../types';
import { SignIn } from '../SignIn';
import { SignUp } from '../SignUp';
import { Home } from './index';

const Stack = createNativeStackNavigator<RootStackParamList>();

const component = (
  <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen
        name="Root"
        component={Home}
      />
      <Stack.Screen
        name="SignIn"
        component={SignIn}
      />
      <Stack.Screen
        name="SignUp"
        component={SignUp}
      />
    </Stack.Navigator>
  </NavigationContainer>
);

const setup = () => {
  return render(component)
}

describe('src > screens > Home', () => {
  it(`renders correctly`, () => {
    const tree = setup().toJSON();

    expect(tree).toMatchSnapshot();
  });

  it('should render correctly', () => {
    setup();

    expect(screen.getByText(/s\.o\./i)).toBeOnTheScreen()
    expect(screen.getByText(/ordem de serviço/i)).toBeOnTheScreen()
    expect(screen.getByText(/gerencie e organize seu negócio de um só lugar\./i)).toBeOnTheScreen()
    expect(screen.getByText(/log in/i)).toBeOnTheScreen()
    expect(screen.getByText(/sign up/i)).toBeOnTheScreen()
  })

  it('should navigate to Sign In screen', () => {
    setup();

    const signInButton = screen.getByText(/log in/i)
    fireEvent.press(signInButton)

    expect(screen.getByText(/Bem vindo/i)).toBeOnTheScreen()
    expect(screen.getByText(/de volta!/i)).toBeOnTheScreen()
  })

  it('should navigate to Sign Up screen', () => {
    setup();

    const signUpButton = screen.getByText(/sign up/i)
    fireEvent.press(signUpButton)
    expect(screen.getByText(/card aqui/i)).toBeOnTheScreen()
  })
})
