import { Text, View } from "react-native";
import { UIButton } from "../../components/UI/UIButton";

function Home() {
  const onPress = () => {
    console.log("Pressed");
  }

  return (
    <View className="flex justify-between bg-blue-RYB px-4 py-8 h-full">
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
            icon="mug-saucer"
            onPress={onPress}
            size="extra-large"
            variant="white"
          >
            Log in
          </UIButton>

          <UIButton
            onPress={onPress}
            size="extra-large"
            variant="secondary"
          >
            Sign up
          </UIButton>
        </View>
      </View>
    </View>
  )
}

export { Home };
