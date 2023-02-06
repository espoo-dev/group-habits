import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { Text, View } from "react-native";
import { RootStackParamList } from "../../../types";
import { UIButton } from "../../components/UI/UIButton";
import { UIContainer } from "../../components/UI/UIContainer";

type Props = NativeStackScreenProps<RootStackParamList, 'Root'>;

function Home({ navigation }: Props) {
  const handleSignIn = () => {
    navigation.navigate('SignIn')
  }

  const handleSignUp = () => {
    navigation.navigate('SignUp')
  }

  return (
    <UIContainer className="flex justify-between bg-blue-RYB px-4 py-8 h-full">
      <Text className="text-lotion font-bold text-2xl mt-4">
        S.O.
      </Text>

      <View>
        <Text className="text-lotion font-bold text-2xl">
          Ordem de Serviço
        </Text>

        <Text className="text-lotion mt-1">
          Gerencie e organize seu negócio de um só lugar.
        </Text>

        <View className="flex justify-between bg-blue-RYB mt-12 h-[104px]">
          <UIButton
            onPress={handleSignIn}
            size="extra-large"
            variant="secondary"
          >
            Log in
          </UIButton>

          <UIButton
            onPress={handleSignUp}
            size="extra-large"
            variant="secondary"
          >
            Sign up
          </UIButton>
        </View>
      </View>
    </UIContainer>
  )
}

export { Home };
