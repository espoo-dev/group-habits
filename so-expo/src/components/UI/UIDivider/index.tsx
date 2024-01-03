import { Text, View } from "react-native";
import { classNames } from "../../../utils/strings";

interface IUIDividerProps {
  className?: string;
}

function UIDivider({ className = "" }: IUIDividerProps) {
  return (
    <View className={classNames(
      "relative flex items-center justify-center",
      className,
    )}>
      <View
        className="absolute top-0 right-0 bottom-0 left-0 flex items-center justify-center"
        aria-hidden={true}>
        <View className="w-full border-t border-gray-300" />
      </View>

      <View className="relative flex items-center justify-center">
        <Text className="bg-white px-2 text-sm text-gray-500">
          Ou
        </Text>
      </View>
    </View>
  )
}

export { UIDivider };
