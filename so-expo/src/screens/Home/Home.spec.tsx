// import renderer from 'react-test-renderer';
import { NavigationContainer } from '@react-navigation/native';
import { render } from '@testing-library/react-native';
import { Home } from './index';

const component = (
  <NavigationContainer>
    <Home />
  </NavigationContainer>
);


it(`renders correctly`, () => {
  const tree = render(component).toJSON();

  expect(tree).toMatchSnapshot();
});
