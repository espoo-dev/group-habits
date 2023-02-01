import { StyleSheet } from "react-native";
import { Text, View } from "../../components/Themed";

function SignUp() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>
        Login
      </Text>

      <View style={styles.card}>
        <Text>
          Card aqui
        </Text>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  card: {
    backgroundColor: 'red',
    borderRadius: 8,
    marginTop: 10,
    padding: 18,
  },
})

export { SignUp };
