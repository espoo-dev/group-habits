class AddHabitsToGroup < ActiveRecord::Migration[7.0]
  def change
    add_column :habits, :group_id, :integer
    add_index  :habits, :group_id
  end
end
