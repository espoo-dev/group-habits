if Rails.env.development?
  AdminUser.create!(email: 'admin@example.com', password: 'password')
  group = Group.create!(name: 'Espoo lovers')
  Habit.create!(name: 'Drink water', group:)
  Habit.create!(name: 'Exercise', group:)
  Habit.create!(name: 'Eat 2 fruits', group:)
  User.create!(email: 'user@email.com', password: '123456789', group:)

  Setting.create_or_find_by!(key: 'min_version', value: '0.0')
end
