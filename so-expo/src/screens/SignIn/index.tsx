import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { LinearGradient } from 'expo-linear-gradient';
import { RootStackParamList } from '../../../types';
import { UIButton } from '../../components/UI/UIButton';
import { UIContainer } from '../../components/UI/UIContainer';

type Props = NativeStackScreenProps<RootStackParamList, 'SignIn'>;

function SignIn({ navigation }: Props) {
  const handleSignIn = () => {
    console.log('Opá')
  }

  const handleSignUp = () => {
    navigation.navigate('SignUp')
  }

  return (
    <LinearGradient
      colors={['#1A54F8', '#FBFBFC']}
      className="h-full inline-flex items-center justify-end bg-lotion"
    >
      <UIContainer className="w-full inline-flex justify-between mt-12 h-[174px]">
        <UIButton
          onPress={handleSignIn}
          size="extra-large"
        >
          Log in
        </UIButton>

        <UIButton
          onPress={handleSignUp}
          size="extra-large"
        >
          Sign up
        </UIButton>
      </UIContainer>
    </LinearGradient>
  )
}

export { SignIn };
