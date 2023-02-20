import { Text, View } from "react-native";
import { UIButton } from "../../components/UI/UIButton";
import { UIContainer } from "../../components/UI/UIContainer";
import { useAuth } from "../../hooks/useAuth";

function Dashboard() {
  const { logout } = useAuth()
  const handleLogout = () => {
    logout()
  }

  return (
    <UIContainer className="flex justify-between bg-blue-RYB h-full">
      <Text className="text-lotion font-bold text-4xl mt-4">
        S.O.
      </Text>

      <View>
        <Text className="text-lotion font-bold text-3xl">
          Dashboard
        </Text>

        <Text className="text-lotion mt-1 text-base">
          Gerencie e organize seu negócio de um só lugar.
        </Text>

        <UIButton
          className="w-full mt-3"
          onPress={handleLogout}
          size="extra-large"
          variant="secondary"
        >
          Logout
        </UIButton>
      </View>
    </UIContainer>
  )
}

export { Dashboard };
