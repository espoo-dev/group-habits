import {
  Mukta_700Bold, useFonts
} from '@expo-google-fonts/mukta';
import { ReactNode, useMemo } from 'react';
import { GestureResponderEvent, Pressable, StyleSheet, Text } from "react-native";
import Colors from "../../../constants/Colors";

interface IUIButton {
  children: ReactNode;
  onPress: (event: GestureResponderEvent) => void
}

function UIButton({ children, onPress }: IUIButton) {
  const [fontsLoaded] = useFonts({
    Mukta_700Bold,
  });
  const colors = Colors.colors
  const styles = useMemo(() => stylesFactory({ colors }), [Colors])

  return <Pressable
    onPress={onPress}
    style={styles.button}
  >
    {fontsLoaded ?
      <Text style={styles.text}>
        {children}
      </Text> :
      <></>
    }
  </Pressable>
}

interface IStyleSheet {
  colors: typeof Colors.colors;
}

const stylesFactory = ({ colors }: IStyleSheet) => StyleSheet.create({
  button: {
    alignItems: 'center',
    backgroundColor: colors.lotion,
    borderRadius: 4,
    flex: 1,
    justifyContent: 'center',
    paddingHorizontal: 4,
    paddingVertical: 8,
  },
  text: {
    color: colors.blueRYB,
    fontFamily: 'Mukta_700Bold',
    fontSize: 14,
  }
})

export { UIButton };
