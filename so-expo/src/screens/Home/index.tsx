import { useMemo } from "react";
import { StyleSheet, Text, View } from "react-native";
import { UIButton } from "../../components/UI/UIButton";
import Colors from '../../constants/styles/Colors';


function Home() {
  const { colors } = Colors

  const styles = useMemo(() => stylesFactory({ colors }), [Colors])

  const onPress = () => {
    console.log("Pressed");
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>
        S.O.
      </Text>

      <View>
        <Text style={styles.title}>
          Ordem de Serviço
        </Text>

        <Text style={styles.subtitle}>
          Gerencie e organize seu negócio de um só lugar.
        </Text>

        <View style={styles.actions}>
          <UIButton
            onPress={onPress}
            size="medium"
            weight="bold"
          >
            Log in
          </UIButton>

          <UIButton
            onPress={onPress}
            size="large"
            weight="bold"
          >
            Sign up
          </UIButton>
        </View>
      </View>
    </View>
  )
}

const stylesFactory = ({ colors }: any) => StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-between',
    backgroundColor: '#1A54F8',
    paddingHorizontal: 16,
    paddingVertical: 32,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    color: "#FBFBFC",
  },
  subtitle: {
    fontSize: 12,
    color: "#FBFBFC",
    marginTop: 8,
  },
  actions: {
    flex: 1,
    backgroundColor: '#1A54F8',
    justifyContent: 'space-between',
    gap: 8,
    marginTop: 64,
  },
})

export { Home };
