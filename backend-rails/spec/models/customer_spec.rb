# == Schema Information
#
# Table name: customers
#
#  id                :bigint           not null, primary key
#  name              :string           not null
#  document_number   :string           not null
#  phone             :string           not null
#  state_inscription :string
#  customer_type     :integer          not null
#  user_id           :string           not null
#  created_at        :datetime         not null
#  updated_at        :datetime         not null
#
# Indexes
#
#  index_customers_on_user_id                      (user_id)
#  index_customers_on_user_id_and_document_number  (user_id,document_number) UNIQUE
#  index_customers_on_user_id_and_name             (user_id,name) UNIQUE
#
require 'rails_helper'

RSpec.describe Customer, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
  end

  context 'validations' do
    context 'presence' do
      it { should validate_presence_of(:name) }
      it { should validate_presence_of(:document_number) }
      it { should validate_presence_of(:phone) }
      it { should define_enum_for(:customer_type) }
    end

    context 'uniqueness' do
      subject { create(:customer) }
      it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
      it { should validate_uniqueness_of(:document_number).scoped_to(:user_id).case_insensitive }
    end
  end
end
