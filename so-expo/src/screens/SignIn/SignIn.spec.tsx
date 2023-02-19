import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { fireEvent, render, screen } from '@testing-library/react-native';
import { RootStackParamList } from '../../../types';
import * as authService from "../../services/auth";
import { SignUp } from '../SignUp';
import { SignIn } from './index';

const Stack = createNativeStackNavigator<RootStackParamList>();

const component = (
  <NavigationContainer>
    <Stack.Navigator>
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

describe('src > screens > SignIn', () => {
  it(`renders correctly`, () => {
    const tree = setup().toJSON();

    expect(tree).toMatchSnapshot();
  });

  it('should render correctly', () => {
    setup();

    expect(screen.getByText(/Bem vindo/i)).toBeOnTheScreen()
    expect(screen.getByText(/de volta!/i)).toBeOnTheScreen()
    expect(screen.getByText(/sign in/i)).toBeOnTheScreen()
    expect(screen.getByText(/sign up/i)).toBeOnTheScreen()
    expect(screen.getByTestId('email-input')).toBeOnTheScreen()
    expect(screen.getByTestId('password-input')).toBeOnTheScreen()
  })

  it('should navigate to Sign Up screen', () => {
    setup();

    const signUpButton = screen.getByText(/sign up/i)
    fireEvent.press(signUpButton)
    expect(screen.getByText(/card aqui/i)).toBeOnTheScreen()
  })

  describe('when login button is pressed', () => {
    describe('and email and password is filled', () => {
      let authServiceSpy: any;

      beforeEach(() => {
        authServiceSpy = jest.spyOn(authService, 'auth')
        setup();
        const email = screen.getByTestId('email-input')
        const password = screen.getByTestId('password-input')

        // fireEvent.changeText(email, 'email@email.com')
        // fireEvent.changeText(password, 'secret')
        fireEvent.changeText(email, 'user@email.com')
        fireEvent.changeText(password, '123456789')

        const signInButton = screen.getByText(/sign in/i)
        fireEvent.press(signInButton)
      })

      it('should navigate to Dashboard Page', async () => {
        const expectedProps = {
          "user": {
            "email": "user@email.com",
            "password": "123456789"
          }
        }

        expect(authServiceSpy).toBeCalledWith(expectedProps)
      })
    })
  })
})
