import { NativeStackScreenProps } from '@react-navigation/native-stack';
import isEmpty from 'lodash/isEmpty';
import { useContext, useState } from 'react';
import { Keyboard, KeyboardAvoidingView, Platform, Text, TextInput, TouchableWithoutFeedback, View } from 'react-native';
import { RootStackParamList } from '../../../types';
import { UIButton } from '../../components/UI/UIButton';
import { UIContainer } from '../../components/UI/UIContainer';
import { UIDivider } from '../../components/UI/UIDivider';
import { AuthContext } from '../../contexts/AuthContext';

type Props = NativeStackScreenProps<RootStackParamList, 'SignIn'>;

function SignIn({ navigation }: Props) {
  const { auth, loading } = useContext(AuthContext)
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleSignIn = () => {
    if (isEmpty(email) || isEmpty(password)) return;

    const payload = {
      user: {
        email,
        password,
      }
    }

    auth(payload)
  }

  const handleSignUp = () => {
    navigation.navigate('SignUp')
  }

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
      className="bg-lotion"
    >
      <TouchableWithoutFeedback
        onPress={Keyboard.dismiss}
      >
        <View className="relative h-full w-full inline-flex items-center justify-between pb-8">
          <UIContainer
            className="absolute z-10 flex items-start justify-start h-1/5 bg-blue-RYB pt-2 px-6"
            paddingNone={true}
          >
            <Text className="text-lotion font-bold text-3xl mt-2">
              Bem vindo
            </Text>
            <Text className="text-lotion font-bold text-2xl bg-gray-700 p-2">
              de volta!
            </Text>
          </UIContainer>

          <UIContainer
            className="w-full h-full flex items-center bg-lotion px-6"
            paddingNone={true}
          >
            <View className="flex items-center justify-end w-full h-full">
              <View className="w-full">
                <Text className="block text-sm font-medium text-gray-700">
                  Email
                </Text>
                <View className="w-full mt-1">
                  <TextInput
                    autoCapitalize='none'
                    className="block w-full pl-4 h-10 rounded-md border border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    keyboardType="email-address"
                    onChangeText={setEmail}
                    placeholder="minha-empresa@example.com"
                    placeholderTextColor="gray"
                    testID="email-input"
                    value={email}
                  />
                </View>
              </View>

              <View className="w-full mt-2">
                <Text className="block text-sm font-medium text-gray-700">
                  Senha
                </Text>
                <View className="w-full mt-1">
                  <TextInput
                    autoCapitalize='none'
                    className="block w-full pl-4 h-10 rounded-md border border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    keyboardType="default"
                    onChangeText={setPassword}
                    placeholder="****************"
                    placeholderTextColor="gray"
                    secureTextEntry={true}
                    testID="password-input"
                    value={password}
                  />
                </View>
              </View>

              <UIButton
                className="w-full mt-3"
                loading={loading}
                onPress={handleSignIn}
                size="extra-large"
                disabled={loading}
              >
                Sign in
              </UIButton>

              <UIDivider className="w-full mt-2" />

              <View className="w-full mt-2">
                <UIButton
                  className="w-full"
                  onPress={handleSignUp}
                  size="extra-large"
                  variant="secondary"
                >
                  Sign up
                </UIButton>
              </View>
            </View>
          </UIContainer>
        </View>
      </TouchableWithoutFeedback>
    </KeyboardAvoidingView>
  )
}

export { SignIn };
