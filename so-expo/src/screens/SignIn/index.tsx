import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { Text, View } from 'react-native';
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
    <View className="h-full w-full inline-flex items-center justify-between">
      <UIContainer className="w-full h-1/2 inline-flex justify-center bg-blue-RYB">
        <Text className="text-lotion font-bold text-4xl mt-4">
          Bem vindo
        </Text>
        <Text className="text-lotion font-bold text-2xl mt-4">
          de volta!
        </Text>
      </UIContainer>
      <UIContainer className="w-full h-1/2 inline-flex items-center justify-end bg-lotion">
        <View className="w-full inline-flex justify-between h-[104px]">
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
        </View>
      </UIContainer>
    </View>
  )
}

export { SignIn };
