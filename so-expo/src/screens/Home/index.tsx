import { useNavigation } from "@react-navigation/native";
import { Text, View } from "react-native";
import { UIButton } from "../../components/UI/UIButton";
import { UIContainer } from "../../components/UI/UIContainer";

function Home() {
  const navigation = useNavigation();

  const handleSignIn = () => {
    navigation.navigate('SignIn')
  }

  const handleSignUp = () => {
    navigation.navigate('SignUp')
  }

  return (
    <UIContainer className="flex justify-between bg-blue-RYB h-full">
      <Text className="text-lotion font-bold text-4xl mt-4">
        S.O.
      </Text>

      <View>
        <Text className="text-lotion font-bold text-3xl">
          Ordem de Serviço
        </Text>

        <Text className="text-lotion mt-1 text-base">
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